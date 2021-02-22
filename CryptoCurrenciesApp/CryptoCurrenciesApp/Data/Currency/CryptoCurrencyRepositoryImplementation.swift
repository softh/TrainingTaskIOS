//
//  CryptoCurrencyRepositoryImplementation.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 28.01.21.
//

import Foundation
import RxSwift

class CryptoCurrencyRepositoryImplementation : CryptoCurrencyRepository {
    
    private let networkDataSource: CryptoCurrencyNetworkSource
    private let cacheDataSource: CryptoCurrencyCacheDataSource
    
    init(networkDataSource: CryptoCurrencyNetworkSource, cacheDataSource: CryptoCurrencyCacheDataSource) {
        self.networkDataSource = networkDataSource
        self.cacheDataSource = cacheDataSource
    }
    
    func getCryptoCurrenciesList(countOfItems: Int) -> Single<[CryptoCurrencyModel]> {
        
        let networkSingleStream = networkDataSource.getCryptoCurrenciesList(countOfItems: countOfItems)
            .do(onSuccess: checkErrorCodeConsumer)
            .do(onSuccess: {self.cacheDataSource.setCryptoCurrenciesList(list: $0.data)})
            .map{$0.data}
        
        let cacheSingleStream = cacheDataSource.getCryptoCurrenciesList(countOfItems: countOfItems)
            .filter{!$0.isEmpty}
            
        
        return Observable.concat([cacheSingleStream.asObservable(), networkSingleStream.asObservable()]).take(1).asSingle().map(domainMapper)
    }
    
    private let checkErrorCodeConsumer: (BaseResponse<CryptoCurrencyBean>) throws -> Void = { response in
        if(response.responseStatus.errorCode != 0) {
            throw ApiError(response.responseStatus.errorMessage)
        }
    }
    
    private let domainMapper: ([CryptoCurrencyBean]) -> [CryptoCurrencyModel] = { beans in
        var result = [CryptoCurrencyModel]()
        beans.forEach { data in
            let quote = data.quote.quoteInUSD
            let model = CryptoCurrencyModel(id: data.id, name: data.name, symbol: data.symbol,
                    currentPrice: quote.currentPrice, percentChangeByHour: quote.percentChangeByHour,
                    percentChangeByDay: quote.percentChangeByDay, percentChangeByWeek: quote.percentChangeByWeek,
                    percentChangeByMonth: quote.percentChangeByMonth, totalCapitalization: quote.totalCapitalization,
                    updatedDate: quote.updatedDate)
            
            result.append(model)
        }
        
        return result
    }
    
    struct ApiError : Error {
        let errorMessage: String?
        
        init(_ errorMessage: String? = nil) {
            self.errorMessage = errorMessage
        }
    }
}
