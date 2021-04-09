package by.st.kmm.sdk.data.currency.source.local.memory

import by.st.kmm.sdk.data.currency.CryptoCurrencyDto
import by.st.kmm.sdk.data.currency.source.local.CryptoCurrencyBaseLocalDataSource
import by.st.kmm.sdk.data.currency.source.local.CryptoCurrencyLocalDataSource
import kotlinx.datetime.Clock

private const val DEFAULT_CACHE_LIFETIME: Long = 10 * 60 * 1000

/**
 * @author RamashkaAE
 */
class CryptoCurrencyMemorySource(cacheLifetime: Long = DEFAULT_CACHE_LIFETIME) : CryptoCurrencyBaseLocalDataSource(cacheLifetime) {

    private val cacheBuffer = mutableListOf<CryptoCurrencyDto>()

    override fun getCryptoCurrencies(countOfElements: Int): List<CryptoCurrencyDto> {
        if(isCacheExpired()) {
            this.cacheBuffer.clear()
        }
        return cacheBuffer
    }

    override fun saveCryptoCurrencies(items: List<CryptoCurrencyDto>) {
        this.cacheBuffer.clear()
        this.cacheBuffer.addAll(items)
        dropLastUpdatedTime()
    }
}