package by.st.kmm.sdk.data.currency.repository

import by.st.kmm.sdk.data.cache.LogoStorageDatabase
import by.st.kmm.sdk.data.currency.CryptoCurrencyDto
import by.st.kmm.sdk.data.currency.source.local.CryptoCurrencyLocalDataSource
import by.st.kmm.sdk.data.currency.source.local.memory.EmptyStubCurrencyMemorySource
import by.st.kmm.sdk.data.currency.source.remote.CryptoCurrencyRemoteDataSource
import by.st.kmm.sdk.data.currency.toDomainModel
import by.st.kmm.sdk.data.response.BaseListResponse
import by.st.kmm.sdk.domain.currency.CryptoCurrencyModel
import by.st.kmm.sdk.domain.currency.CryptoCurrencyRepository
import by.st.kmm.sdk.domain.currency.RepositoryException
import com.badoo.reaktive.observable.*
import com.badoo.reaktive.single.*

/**
 * @author RamashkaAE
 */
class CoinCapCryptoCurrencyRepository(
    private val localSource: CryptoCurrencyLocalDataSource = EmptyStubCurrencyMemorySource(),
    private val remoteSource: CryptoCurrencyRemoteDataSource,
    private val logoStorage: LogoStorageDatabase
) : CryptoCurrencyRepository {

    override fun getCryptoCurrenciesList(countOfItems: Int): Single<List<CryptoCurrencyModel>> {
        return remoteSource.getCryptoCurrencies(countOfItems).doOnAfterSuccess { checkResponse(it) }.map { it.data.toDomainModel() }
//        return concat(
//            localSource.getCryptoCurrencies(countOfItems),
//            remoteSource.getCryptoCurrencies(countOfItems)
//                .doOnAfterSuccess { response ->
//                    checkResponse(response)
//                    response.data?.let { data ->
//                        localSource.saveCryptoCurrencies(data) //todo without rx inside
//                    }
//                }
//                .map { it.data!! })
//            .firstOrDefault(listOf())
//            .map { it.toDomainModel() }
    }


    override fun startInitialization(countOfItems: Int): Observable<Int> {
//        logoStorage.clearStorage()
//        val ids = getCryptoCurrenciesList(countOfItems).map { it.id }
//        var step = 0
//        val observable = observableFromFunction {
//
//        }
//
//
//
//        val logos = remoteSource.getCurrenciesLogo(ids.toIntArray()) {}
//        logos.data?.values?.forEach { logo ->
//            val payload = remoteSource.loadCurrencyLogo(logo.logoUrl)
//            logoStorage.insertLogo(CurrencyLogoPayload(logo.currencyId, payload))
//            initializationProgressListener?.onProgress(step++)
//        }
        return observable { }
    }

    private fun checkResponse(response: BaseListResponse<*>) {
        if (response.responseStatus.errorCode != 0) {
            throw RepositoryException(response.responseStatus.errorCode, response.responseStatus.errorMessage)
        }
    }
}
