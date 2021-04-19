package by.st.kmm.sdk.domain.currency

import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.single.Single

/**
 * @author RamashkaAE
 */
interface CryptoCurrencyRepository {

    fun getCryptoCurrenciesList(countOfItems: Int) : Single<List<CryptoCurrencyModel>>

    fun startInitialization(countOfItems: Int) : Observable<Int>
}

class RepositoryException(val errorCode: Int, val errorMessage: String?) : Exception(errorMessage)