package by.st.kmm.sdk.data.currency

import by.st.kmm.sdk.data.currency.CryptoCurrencyDto

/**
 * @author RamashkaAE
 */
interface CryptoCurrencyDataSource {

    fun getAllCryptoCurrencies() : List<CryptoCurrencyDto>
}