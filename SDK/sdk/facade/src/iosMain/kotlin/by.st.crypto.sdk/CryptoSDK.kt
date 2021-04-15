//package by.st.crypto.sdk
//
//import by.st.kmm.sdk.domain.currency.CryptoCurrencyModel
//import by.st.kmm.sdk.domain.currency.CryptoCurrencyRepository
//import kotlin.native.concurrent.freeze
//
//actual class CryptoSDK actual constructor(private val dataRepository: CryptoCurrencyRepository) : CryptoSDKProtocol {
//
//    init {
//        //dataRepository.freeze()
////        this.freeze()
//    }
//
//    override suspend fun getCryptoCurrenciesList(countOfItems: Int): List<CryptoCurrencyModel> {
//        try {
//            return dataRepository.getCryptoCurrenciesList(countOfItems)//.freeze()
//        } catch (error: Throwable) {
//            throw  error//.freeze()
//        }
//    }
//}