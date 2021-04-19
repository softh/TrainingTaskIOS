package by.st.kmm.sdk.data.currency.source.remote

import by.st.kmm.sdk.data.common.net.HttpClientFactory
import by.st.kmm.sdk.data.currency.CryptoCurrencyDto
import by.st.kmm.sdk.data.currency.CurrencyLogoDto
import by.st.kmm.sdk.data.response.BaseListResponse
import by.st.kmm.sdk.data.response.BaseMapResponse
import by.st.kmm.sdk.tools.concurrency.BackgroundDispatcher
import by.st.kmm.sdk.tools.concurrency.MainDispatcher
import by.st.kmm.sdk.tools.concurrency.RxExtension
import com.badoo.reaktive.single.Single
import io.ktor.client.request.*
import io.ktor.utils.io.core.*

/**
 * @author RamashkaAE
 */
class CryptoCurrencyNetworkSource(
    private val apiEndpoint: String,
    private val apiToken: String,
    private val isLogEnabled: Boolean = false
) : CryptoCurrencyRemoteDataSource {

    /**
     * Temporary fix for iOS
     */
    private fun getHttpClient() = HttpClientFactory.createHttpClient(
        apiToken = apiToken,
        isLogEnabled = isLogEnabled
    )

    override fun getCryptoCurrencies(
        countOfElements: Int,
    ): Single<BaseListResponse<CryptoCurrencyDto>> {
        return RxExtension.singleFromCoroutineUnsafe(BackgroundDispatcher) {
            getHttpClient().use {
                val result = it.get {
                    url("$apiEndpoint/cryptocurrency/listings/latest")
                    parameter("limit", countOfElements)
                } as BaseListResponse<CryptoCurrencyDto>

                result
            }
        }
    }

    override fun getCurrenciesLogo(
        currencyIds: IntArray,
    ): Single<BaseMapResponse<CurrencyLogoDto>> {
        return RxExtension.singleFromCoroutineUnsafe(BackgroundDispatcher) {
            getHttpClient().use {
                val result = it.get {
                    url("$apiEndpoint/cryptocurrency/info")
                    parameter("aux", "logo")
                    parameter("id", currencyIds.joinToString(separator = ","))
                } as BaseMapResponse<CurrencyLogoDto>

                result
            }
        }
    }

    override fun loadCurrencyLogo(logoUrl: String) : Single<ByteArray>{
        return RxExtension.singleFromCoroutineUnsafe(BackgroundDispatcher) {
            getHttpClient().use {
                val response: ByteArray = it.get {
                    url(logoUrl)
                }
                response
            }
        }
    }
}