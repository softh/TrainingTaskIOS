import Foundation
import RxSwift

class CryptoCurrencyDatabaseSource: CryptoCurrencyDataSource {
    func getCryptoCurrenciesList(countOfItems: Int) -> Single<BaseResponse<CryptoCurrencyBean>> {
        return Single.error(StubError("Not implemented yet!"))
    }
    
    struct StubError : Error {
        let message: String?
        
        init(_ errorMessage: String? = "") {
            self.message = errorMessage
        }
    }
}
