//package by.st.crypto.sdk
//
//import by.st.kmm.sdk.domain.currency.CryptoCurrencyModel
//import by.st.kmm.sdk.domain.currency.CryptoCurrencyRepository
//
//actual class CryptoSDK actual constructor(private val dataRepository: CryptoCurrencyRepository) : CryptoSDKProtocol {
//
//    override suspend fun getCryptoCurrenciesList(countOfItems: Int): List<CryptoCurrencyModel> {
//        return dataRepository.getCryptoCurrenciesList(countOfItems)
//    }
//}