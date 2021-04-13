package by.st.kmm.sdk.data.currency.repository

import by.st.kmm.sdk.data.cache.LogoStorageDatabase
import by.st.kmm.sdk.data.currency.CurrencyLogoPayload
import by.st.kmm.sdk.data.currency.source.local.CryptoCurrencyLocalDataSource
import by.st.kmm.sdk.data.currency.source.remote.CryptoCurrencyRemoteDataSource
import by.st.kmm.sdk.data.currency.toDomainModel
import by.st.kmm.sdk.data.response.BaseListResponse
import by.st.kmm.sdk.domain.currency.*

/**
 * @author RamashkaAE
 */
class CoinCapCryptoCurrencyRepository(
    private val localSource: CryptoCurrencyLocalDataSource? = null,
    private val remoteSource: CryptoCurrencyRemoteDataSource,
    private val logoStorage: LogoStorageDatabase
) : CryptoCurrencyRepository {

    override suspend fun getCryptoCurrenciesList(countOfItems: Int): List<CryptoCurrencyModel> {
        localSource?.let {
            val cachedItems = it.getCryptoCurrencies(countOfItems)
            if (!cachedItems.isNullOrEmpty()) {
                return cachedItems.map { dto ->
                    val logoData = logoStorage.getLogoByCurrencyId(dto.id)?.logoPayload
                    dto.toDomainModel(logoData)
                }
            } else {
                val remoteItems = remoteSource.getCryptoCurrencies(countOfItems, ::checkResponse).data
                remoteItems?.let {
                    localSource.saveCryptoCurrencies(remoteItems)
                }

                val models = mutableListOf<CryptoCurrencyModel>()
                remoteItems?.forEach { item ->
                    val logoData = logoStorage.getLogoByCurrencyId(item.id)?.logoPayload
                    models.add(item.toDomainModel(logoData))
                }

                return models
            }

        } ?: return remoteSource.getCryptoCurrencies(countOfItems, ::checkResponse).data?.map {
            it.toDomainModel(logoStorage.getLogoByCurrencyId(it.id)?.logoPayload)
        } ?: throw RepositoryException(
            0,
            "Unexpected error"
        )
    }

    override suspend fun startInitialization(countOfItems: Int, initializationProgressListener: RepositoryInitializationProgressListener?) {
        var step = 0
        logoStorage.clearStorage()
        val ids = getCryptoCurrenciesList(countOfItems).map { it.id }
        val logos = remoteSource.getCurrenciesLogo(ids.toIntArray()) {}
        logos.data?.values?.forEach { logo ->
            val payload = remoteSource.loadCurrencyLogo(logo.logoUrl)
            logoStorage.insertLogo(CurrencyLogoPayload(logo.currencyId, payload))
            initializationProgressListener?.onProgress(step++)
        }
    }

    private fun checkResponse(response: BaseListResponse<*>) {
        if (response.responseStatus.errorCode != 0) {
            throw RepositoryException(response.responseStatus.errorCode, response.responseStatus.errorMessage)
        }
    }
}
