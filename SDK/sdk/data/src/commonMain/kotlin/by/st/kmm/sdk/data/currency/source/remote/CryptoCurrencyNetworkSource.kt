package by.st.kmm.sdk.data.currency.source.remote

import by.st.kmm.sdk.data.common.net.HttpClientFactory
import by.st.kmm.sdk.data.currency.CryptoCurrencyDto
import by.st.kmm.sdk.data.response.BaseResponse
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

    override suspend fun getCryptoCurrencies(
        countOfElements: Int,
        completionBlock: (response: BaseResponse<CryptoCurrencyDto>) -> Unit
    ): BaseResponse<CryptoCurrencyDto> {
        return getHttpClient().use {
            val result = it.get("$apiEndpoint/cryptocurrency/listings/latest?limit=$countOfElements")
                    as BaseResponse<CryptoCurrencyDto>
            completionBlock.invoke(result)
            result
        }
    }
}