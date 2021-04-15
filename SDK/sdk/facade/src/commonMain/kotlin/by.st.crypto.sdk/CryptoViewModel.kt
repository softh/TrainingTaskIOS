package by.st.crypto.sdk

import by.st.crypto.sdk.tools.concurency.Background
import by.st.kmm.sdk.domain.currency.CryptoCurrencyModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CryptoViewModel(private val cryptoSDKProtocol: CryptoSDKProtocol) {

    private val _countState = MutableStateFlow<List<CryptoCurrencyModel>>(mutableListOf())
    val countState: StateFlow<List<CryptoCurrencyModel>> = _countState

    @InternalCoroutinesApi
    fun loadCurrencies(count: Int) {
        GlobalScope.launch(Background) {
            cryptoSDKProtocol.getCryptoCurrenciesList(count)
        }
    }
}

