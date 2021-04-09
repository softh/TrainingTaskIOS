package by.st.kmm.sdk.data.currency.source.local

import kotlinx.datetime.Clock

/**
 * @author RamashkaAE
 */
abstract class CryptoCurrencyBaseLocalDataSource(private val cacheLifetime: Long) :
    CryptoCurrencyLocalDataSource {

    private var lastUpdatedTime = 0L

    init {
        dropLastUpdatedTime()
    }

    protected fun isCacheExpired(): Boolean {
        return Clock.System.now().toEpochMilliseconds() - lastUpdatedTime > cacheLifetime
    }

    protected fun dropLastUpdatedTime() {
        lastUpdatedTime = Clock.System.now().toEpochMilliseconds()
    }

}