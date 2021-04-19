package by.st.kmm.sdk.data.currency.source.remote

import by.st.kmm.sdk.data.currency.CryptoCurrencyDto
import by.st.kmm.sdk.data.currency.CurrencyLogoDto
import by.st.kmm.sdk.data.response.BaseListResponse
import by.st.kmm.sdk.data.response.BaseMapResponse
import com.badoo.reaktive.single.Single

/**
 * @author RamashkaAE
 */
interface CryptoCurrencyRemoteDataSource {

    fun getCryptoCurrencies(countOfElements: Int): Single<BaseListResponse<CryptoCurrencyDto>>

    fun getCurrencyLogo(currencyId: Int): Single<BaseMapResponse<CurrencyLogoDto>>

    fun downloadCurrencyLogo(logoUrl: String) : Single<ByteArray>
}