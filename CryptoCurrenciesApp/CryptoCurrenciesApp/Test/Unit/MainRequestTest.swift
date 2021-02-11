//import XCTest
//
//class MainRequestTest: XCTestCase {
//
//    let url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?limit=50"
//    let token = "07c16939-e6cc-4446-8053-283b35eb91fa"
//
//    let countOfItems = 50
//
//    func testExample() throws {
//
//        let datasource = CryptoCurrencyNetworkSource(apiUrl: url, apiToken: token)
//
//        let repository = CryptoCurrencyRepositoryImplementation(networkDataSource: datasource)
//            
//        let disposable = repository.getCryptoCurrenciesList(countOfItems: countOfItems)
//            .observeOn(MainScheduler())
//            .subscribeOn(SerialDispatchQueueScheduler.init(qos: .background))
//            .subscribe(onSuccess: successConsumer, onError: errorConsumer)
//    }
//
//    func successConsumer(response: [CryptoCurrencyModel]) {
//        print(response)
//    }
//
//    func errorConsumer(error: Error) {
//        prin(error)
//    }
//
//    func testPerformanceExample() throws {
//        // This is an example of a performance test case.
//        self.measure {
//            // Put the code you want to measure the time of here.
//        }
//    }
//
//}
