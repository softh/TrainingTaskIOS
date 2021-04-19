package by.st.crypto.sdk

import by.st.kmm.sdk.data.common.net.InvalidClientRequestException
import by.st.kmm.sdk.data.common.net.RedirectRequestException
import by.st.kmm.sdk.data.common.net.ServerInternalErrorException
import by.st.kmm.sdk.domain.currency.CryptoCurrencyModel
import by.st.kmm.sdk.domain.currency.RepositoryException
import kotlin.coroutines.cancellation.CancellationException

internal const val DEFAULT_COUNT_OF_ITEMS = 300

/**
 * Describes base Crypto currency sdk protocol
 * Contains methods to get and process crypto currencies data
 *
 * @author RamashkaAE
 */
interface CryptoSDKProtocol {

    /**
     * Returns data of crypto currencies as list by [countOfItems] items count
     */
    @Throws(
        CancellationException::class,
        InvalidClientRequestException::class,
        RedirectRequestException::class,
        ServerInternalErrorException::class,
        RepositoryException::class,
        SDKException::class
    )
    suspend fun getCryptoCurrenciesList(countOfItems: Int = DEFAULT_COUNT_OF_ITEMS): List<CryptoCurrencyModel>

    /**
     * Starts sdk initialization (for example caching, image processing etc) for [countOfItems] count of items
     * of crypto currencies
     *
     * Result of progress will be returned in [initializationProgressListener]
     */
    @Throws(
        CancellationException::class,
        InvalidClientRequestException::class,
        RedirectRequestException::class,
        ServerInternalErrorException::class,
        RepositoryException::class,
        SDKException::class
    )
    suspend fun startInitialization(countOfItems: Int = DEFAULT_COUNT_OF_ITEMS, initializationProgressListener: InitializationProgressListener? = null)

    /**
     * Converts price of one crypto currency by [sourceCurrencyId] to other currency by [targetCurrencyId]
     * and returns floating points value.
     */
    @Throws(
        CancellationException::class,
        InvalidClientRequestException::class,
        RedirectRequestException::class,
        ServerInternalErrorException::class,
        RepositoryException::class,
        SDKException::class
    )
    suspend fun convert(sourceCurrencyId: Int, targetCurrencyId: Int) : Double
}

/**
 * Throws if SDK encounters errors
 *
 * @author RamashkaAE
 */
class SDKException(message: String) : Exception(message)

/**
 * Describes progress of sdk initialization
 *
 * @author RamashkaAE
 */
interface InitializationProgressListener {

    /**
     * Notify initialization progress change
     *
     * @param currentStep - The ordinal number of the initialization step relative to the total number of initialized values
     */
    fun onProgress(currentStep: Int)
}