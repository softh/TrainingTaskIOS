package by.st.kmm.sdk.data.currency.source.local

import by.st.kmm.sdk.data.currency.CryptoCurrencyDto

private const val DEFAULT_COUNT_OF_ITEMS = 200

/**
 * @author RamashkaAE
 */
interface CryptoCurrencyLocalDataSource {

    fun getCryptoCurrencies(countOfElements: Int = DEFAULT_COUNT_OF_ITEMS) : List<CryptoCurrencyDto>

    fun saveCryptoCurrencies(items: List<CryptoCurrencyDto>)
}