package by.st.kmm.sdk.data.currency.source.local.memory

import by.st.kmm.sdk.data.currency.CryptoCurrencyDto
import by.st.kmm.sdk.data.currency.source.local.CryptoCurrencyLocalDataSource
import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.completable.completable
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.single
import com.badoo.reaktive.single.singleOf

class EmptyStubCurrencyMemorySource : CryptoCurrencyLocalDataSource {
    override fun getCryptoCurrencies(countOfElements: Int): Single<List<CryptoCurrencyDto>?> {
        return singleOf(null)
    }

    override fun saveCryptoCurrencies(items: List<CryptoCurrencyDto>): Completable {
        return completable {  }
    }
}