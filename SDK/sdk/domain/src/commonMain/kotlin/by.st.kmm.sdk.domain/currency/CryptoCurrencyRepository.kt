package by.st.kmm.sdk.domain.currency

import kotlin.coroutines.cancellation.CancellationException

/**
 * @author RamashkaAE
 */
interface CryptoCurrencyRepository {

    @Throws(RepositoryException::class, CancellationException::class)
    suspend fun getCryptoCurrenciesList(countOfItems: Int) : List<CryptoCurrencyModel>
}

class RepositoryException(val errorCode: Int, val errorMessage: String?) : Exception(errorMessage)