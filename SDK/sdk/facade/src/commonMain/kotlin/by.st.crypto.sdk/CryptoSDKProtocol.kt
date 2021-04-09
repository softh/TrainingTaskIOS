package by.st.crypto.sdk

import by.st.forms.sdk.data.common.net.exception.InvalidClientRequestException
import by.st.forms.sdk.data.common.net.exception.RedirectRequestException
import by.st.forms.sdk.data.common.net.exception.ServerInternalErrorException
import by.st.kmm.sdk.domain.currency.CryptoCurrencyModel
import by.st.kmm.sdk.domain.currency.RepositoryException
import kotlin.coroutines.cancellation.CancellationException

private const val DEFAULT_COUNT_OF_ITEMS = 200

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
}