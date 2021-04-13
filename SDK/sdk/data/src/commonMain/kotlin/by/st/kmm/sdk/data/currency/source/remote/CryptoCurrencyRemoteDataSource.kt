package by.st.kmm.sdk.data.currency.source.remote

import by.st.kmm.sdk.data.currency.CryptoCurrencyDto
import by.st.kmm.sdk.data.currency.CurrencyLogoDto
import by.st.kmm.sdk.data.response.BaseListResponse
import by.st.kmm.sdk.data.response.BaseMapResponse

/**
 * @author RamashkaAE
 */
interface CryptoCurrencyRemoteDataSource {

    suspend fun getCryptoCurrencies(
        countOfElements: Int,
        completionBlock: (response: BaseListResponse<CryptoCurrencyDto>) -> Unit
    ): BaseListResponse<CryptoCurrencyDto>

    suspend fun getCurrenciesLogo(currencyIds: IntArray,
        completionBlock: (response: BaseMapResponse<CurrencyLogoDto>) -> Unit
    ): BaseMapResponse<CurrencyLogoDto>

    suspend fun loadCurrencyLogo(logoUrl: String) : ByteArray
}