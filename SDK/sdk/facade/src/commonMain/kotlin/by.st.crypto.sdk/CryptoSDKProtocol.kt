package by.st.crypto.sdk

import by.st.kmm.sdk.data.common.net.InvalidClientRequestException
import by.st.kmm.sdk.data.common.net.RedirectRequestException
import by.st.kmm.sdk.data.common.net.ServerInternalErrorException
import by.st.kmm.sdk.domain.currency.CryptoCurrencyModel
import by.st.kmm.sdk.domain.currency.RepositoryException
import kotlin.coroutines.cancellation.CancellationException

internal const val DEFAULT_COUNT_OF_ITEMS = 30

/**
 * @author RamashkaAE
 */
interface CryptoSDKProtocol {

    @Throws(
        CancellationException::class,
        InvalidClientRequestException::class,
        RedirectRequestException::class,
        ServerInternalErrorException::class,
        RepositoryException::class
    )
    suspend fun getCryptoCurrenciesList(countOfItems: Int = DEFAULT_COUNT_OF_ITEMS): List<CryptoCurrencyModel>

    suspend fun startInitialization(countOfItems: Int, initializationProgressListener: InitializationProgressListener? = null)
}

interface InitializationProgressListener {
    fun onProgress(currentStep: Int)
}