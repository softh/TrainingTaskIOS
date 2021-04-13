package by.st.crypto.sdk.presentation

import by.st.crypto.sdk.CryptoSDKProtocol
import by.st.crypto.sdk.tools.concurency.Background
import by.st.crypto.sdk.tools.concurency.SuspendWrapper
import by.st.crypto.sdk.tools.concurency.freeze
import by.st.kmm.sdk.domain.currency.CryptoCurrencyModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class TestViewModelIos(private val cryptoSDKProtocol: CryptoSDKProtocol) {

    val scope: CoroutineScope = object : CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = SupervisorJob() + Background
    }

    init {
        freeze()
    }

    fun test() = SuspendWrapper {cryptoSDKProtocol.getCryptoCurrenciesList(200)}

}