package by.st.crypto.sdk.presentation

import by.st.crypto.sdk.CryptoSDKProtocol
import by.st.crypto.sdk.tools.concurency.Background
import by.st.crypto.sdk.tools.concurency.Main
import by.st.kmm.sdk.domain.currency.CryptoCurrencyModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

//class CurrencyListViewModel(private val cryptoSDKProtocol: CryptoSDKProtocol) : BaseViewModel() {
//
//    private val _currenciesListState = MutableStateFlow<List<CryptoCurrencyModel>>(mutableListOf())
//    val currenciesListState: StateFlow<List<CryptoCurrencyModel>> = _currenciesListState
//
//    @InternalCoroutinesApi
//    fun loadCurrencies(count: Int) {
//        GlobalScope.launch(Background + errorHandler) {
//            val result = cryptoSDKProtocol.getCryptoCurrenciesList(count)
//            withContext(Main) {
//                _currenciesListState.value = result
//            }
//        }
//    }
//}