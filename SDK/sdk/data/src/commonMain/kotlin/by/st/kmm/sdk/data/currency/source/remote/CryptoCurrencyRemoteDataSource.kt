package by.st.kmm.sdk.data.currency.source.remote

import by.st.kmm.sdk.data.currency.CryptoCurrencyDto
import by.st.kmm.sdk.data.response.BaseResponse

private const val DEFAULT_COUNT_OF_ITEMS = 200

/**
 * @author RamashkaAE
 */
interface CryptoCurrencyRemoteDataSource {

    suspend fun getCryptoCurrencies(
        countOfElements: Int = DEFAULT_COUNT_OF_ITEMS,
        completionBlock: (response: BaseResponse<CryptoCurrencyDto>) -> Unit
    ): BaseResponse<CryptoCurrencyDto>
}