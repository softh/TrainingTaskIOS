package by.st.kmm.sdk.domain.currency

import kotlin.coroutines.cancellation.CancellationException

/**
 * @author RamashkaAE
 */
interface CryptoCurrencyRepository {

    suspend fun getCryptoCurrenciesList(countOfItems: Int) : List<CryptoCurrencyModel>

    suspend fun startInitialization(countOfItems: Int, initializationProgressListener: RepositoryInitializationProgressListener? = null)
}

class RepositoryException(val errorCode: Int, val errorMessage: String?) : Exception(errorMessage)

interface RepositoryInitializationProgressListener {
    fun onProgress(currentStep: Int)
}