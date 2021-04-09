package by.st.kmm.sdk.data.currency

import by.st.kmm.sdk.data.cache.CryptoCurrencyDatabase

class CryptoCurrencyLocalSource(
    private val databaseInstance: CryptoCurrencyDatabase
) : CryptoCurrencyDataSource {
    override fun getAllCryptoCurrencies(): List<CryptoCurrencyDto> {
        return databaseInstance.getAllCryptoCurrencies()
    }
}