package by.st.kmm.sdk.data.currency.source.remote

import by.st.kmm.sdk.data.currency.CryptoCurrencyDto
import by.st.kmm.sdk.data.currency.CurrencyLogoDto
import by.st.kmm.sdk.data.response.BaseListResponse
import by.st.kmm.sdk.data.response.BaseMapResponse

private const val DEFAULT_COUNT_OF_ITEMS = 200

/**
 * @author RamashkaAE
 */
interface CryptoCurrencyRemoteDataSource {

    suspend fun getCryptoCurrencies(
        countOfElements: Int = DEFAULT_COUNT_OF_ITEMS,
        completionBlock: (response: BaseListResponse<CryptoCurrencyDto>) -> Unit
    ): BaseListResponse<CryptoCurrencyDto>

    suspend fun getCurrenciesLogo(currencyIds: IntArray,
        completionBlock: (response: BaseMapResponse<CurrencyLogoDto>) -> Unit
    ): BaseMapResponse<CurrencyLogoDto>
}