package by.st.kmm.sdk.data.currency.repository

import by.st.kmm.sdk.data.currency.source.local.CryptoCurrencyLocalDataSource
import by.st.kmm.sdk.data.currency.source.remote.CryptoCurrencyRemoteDataSource
import by.st.kmm.sdk.data.currency.toDomainModel
import by.st.kmm.sdk.data.response.BaseResponse
import by.st.kmm.sdk.domain.currency.CryptoCurrencyModel
import by.st.kmm.sdk.domain.currency.CryptoCurrencyRepository
import by.st.kmm.sdk.domain.currency.RepositoryException
import kotlinx.coroutines.flow.flowOf

/**
 * @author RamashkaAE
 */
class CoinCapCryptoCurrencyRepository(
    private val localSource: CryptoCurrencyLocalDataSource? = null,
    private val remoteSource: CryptoCurrencyRemoteDataSource
) : CryptoCurrencyRepository{

    override suspend fun getCryptoCurrenciesList(countOfItems: Int): List<CryptoCurrencyModel> {
        localSource?.let {
            val cachedItems = it.getCryptoCurrencies(countOfItems)
            if(!cachedItems.isNullOrEmpty()) {
                return cachedItems.toDomainModel()
            } else {
                val remoteItems = remoteSource.getCryptoCurrencies(countOfItems, ::checkResponse).data
                remoteItems?.let {
                    localSource.saveCryptoCurrencies(remoteItems)
                }

                return remoteItems.toDomainModel()
            }

        } ?: return remoteSource.getCryptoCurrencies(countOfItems, ::checkResponse).data.toDomainModel()
    }

    private fun checkResponse(response: BaseResponse<*>) {
        if (response.responseStatus.errorCode != 0) {
            throw RepositoryException(response.responseStatus.errorCode, response.responseStatus.errorMessage)
        }
    }
}
