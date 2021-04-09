package by.st.kmm.sdk.data.currency.source.local

import by.st.kmm.sdk.data.cache.CryptoCurrencyDatabase
import by.st.kmm.sdk.data.currency.CryptoCurrencyDto

class CryptoCurrencyLocalSource(
    private val databaseInstance: CryptoCurrencyDatabase
)  {
    fun getCryptoCurrencies(countOfElements: Int): List<CryptoCurrencyDto> {
        return databaseInstance.getAllCryptoCurrencies().take(countOfElements)
    }
}