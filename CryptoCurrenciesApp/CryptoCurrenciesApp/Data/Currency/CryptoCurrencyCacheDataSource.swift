import Foundation
import RxSwift

private let defaultCacheLifetime = 10000

class CryptoCurrencyCacheDataSource : CryptoCurrencyDataSource {
    
    internal typealias T = [CryptoCurrencyBean]
    
    private let cacheLifetime: Int
    
    private var lastUpdateTime: Int = Date().milliseconds
    
    private var cacheBuffer = [CryptoCurrencyBean]()
    
    init(_ cacheLifetime: Int = defaultCacheLifetime) {
        self.cacheLifetime = cacheLifetime
        dropLastUpdatedTime()
    }
    
    func setCryptoCurrenciesList(list: [CryptoCurrencyBean]) {
        self.cacheBuffer = list
        dropLastUpdatedTime()
    }
    
    private func isCacheExpired() -> Bool {
        return (Date().milliseconds - lastUpdateTime > cacheLifetime)
    }
    
    private func dropLastUpdatedTime() {
        lastUpdateTime = Date().milliseconds
    }
    
    func getCryptoCurrenciesList(countOfItems: Int) -> Single<[CryptoCurrencyBean]> {
        if(isCacheExpired()) {
            self.cacheBuffer = [CryptoCurrencyBean]()
        }
        print("Cache buffer: \(cacheBuffer.count)")
        return Single.just(cacheBuffer)
        
    }
}
