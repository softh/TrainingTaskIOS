package by.st.kmm.sdk.data.currency.source.remote

import by.st.kmm.sdk.data.common.net.HttpClientFactory
import by.st.kmm.sdk.data.currency.CryptoCurrencyDto
import by.st.kmm.sdk.data.currency.CurrencyLogoDto
import by.st.kmm.sdk.data.response.BaseListResponse
import by.st.kmm.sdk.data.response.BaseMapResponse
import io.ktor.client.request.*
import io.ktor.utils.io.core.*

private const val MAX_CURRENCIES_LIST_SIZE = 500

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

    override suspend fun getCryptoCurrencies(
        countOfElements: Int,
        completionBlock: (response: BaseListResponse<CryptoCurrencyDto>) -> Unit
    ): BaseListResponse<CryptoCurrencyDto> {
        val count = if (countOfElements > MAX_CURRENCIES_LIST_SIZE) MAX_CURRENCIES_LIST_SIZE else countOfElements
        return getHttpClient().use {
            val result = it.get {
                url("$apiEndpoint/cryptocurrency/listings/latest")
                parameter("limit", count)
            } as BaseListResponse<CryptoCurrencyDto>

            completionBlock.invoke(result)
            result
        }
    }

    override suspend fun getCurrenciesLogo(
        currencyIds: IntArray,
        completionBlock: (response: BaseMapResponse<CurrencyLogoDto>) -> Unit
    ): BaseMapResponse<CurrencyLogoDto> {
        return getHttpClient().use {
            val result = it.get {
                url("$apiEndpoint/cryptocurrency/info")
                parameter("aux", "logo")
                parameter("id", currencyIds.joinToString(separator = ","))
            } as BaseMapResponse<CurrencyLogoDto>

            completionBlock.invoke(result)
            result
        }
    }

    private fun getAllIdsParameter(): String {
        val scope = 1..MAX_CURRENCIES_LIST_SIZE
        return scope.joinToString(separator = ",")
    }
}