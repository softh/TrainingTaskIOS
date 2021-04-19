package by.st.kmm.sdk.data.currency.source.local.memory

import by.st.kmm.sdk.data.currency.CryptoCurrencyDto
import by.st.kmm.sdk.data.currency.source.local.CryptoCurrencyBaseLocalDataSource
import by.st.kmm.sdk.data.currency.source.local.CryptoCurrencyLocalDataSource
import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.completable.completable
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.single
import com.badoo.reaktive.single.singleFromFunction
import kotlinx.datetime.Clock
import kotlin.native.concurrent.SharedImmutable

private const val DEFAULT_CACHE_LIFETIME: Long = 10 * 60 * 1000

/**
 * @author RamashkaAE
 */
class CryptoCurrencyMemorySource(cacheLifetime: Long = DEFAULT_CACHE_LIFETIME) : CryptoCurrencyBaseLocalDataSource(cacheLifetime) {

    private var cacheBuffer: List<CryptoCurrencyDto>? = null

    override fun getCryptoCurrencies(countOfElements: Int): Single<List<CryptoCurrencyDto>?> {
        return single {
            if(isCacheExpired()) {
                this.cacheBuffer = null
            }
            cacheBuffer
        }
    }

    override fun saveCryptoCurrencies(items: List<CryptoCurrencyDto>) : Completable {
        return completable {
            this.cacheBuffer = null
            this.cacheBuffer = items
            dropLastUpdatedTime()
        }
    }
}