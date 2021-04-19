package by.st.kmm.sdk.data.currency.source.local.memory

import by.st.kmm.sdk.data.currency.CryptoCurrencyDto
import by.st.kmm.sdk.data.currency.source.local.CryptoCurrencyBaseLocalDataSource
import by.st.kmm.sdk.data.currency.source.local.CryptoCurrencyLocalDataSource
import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.completable.completable
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.singleFromFunction
import kotlinx.datetime.Clock
import kotlin.native.concurrent.SharedImmutable

private const val DEFAULT_CACHE_LIFETIME: Long = 10 * 60 * 1000

/**
 * @author RamashkaAE
 */
class CryptoCurrencyMemorySource(cacheLifetime: Long = DEFAULT_CACHE_LIFETIME) : CryptoCurrencyBaseLocalDataSource(cacheLifetime) {

    private val cacheBuffer = mutableListOf<CryptoCurrencyDto>()

    override fun getCryptoCurrencies(countOfElements: Int): Single<List<CryptoCurrencyDto>> {
        return singleFromFunction {
            if(isCacheExpired()) {
                this.cacheBuffer.clear()
            }
            cacheBuffer
        }
    }

    override fun saveCryptoCurrencies(items: List<CryptoCurrencyDto>) : Completable {
        return completable {
            this.cacheBuffer.clear()
            this.cacheBuffer.addAll(items)
            dropLastUpdatedTime()
        }
    }
}