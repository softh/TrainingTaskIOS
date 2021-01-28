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
    private let databaseSource: CryptoCurrencyDatabaseSource?
    
    init(networkDataSource: CryptoCurrencyNetworkSource, databaseSource: CryptoCurrencyDatabaseSource? = nil) {
        self.networkDataSource = networkDataSource
        self.databaseSource = databaseSource
    }
    
    func getCryptoCurrenciesList(countOfItems: Int) -> Single<Array<CryptoCurrencyModel>> {
        return networkDataSource.getCryptoCurrenciesList(countOfItems: countOfItems)
            .do(onSuccess: errorCheckConsumer)
            .map(domainMapper)
    }
    
    private let errorCheckConsumer: (BaseResponse<CryptoCurrencyBean>) throws -> Void = { response in
        if(response.responseStatus.errorCode != 0) {
            throw ApiError(response.responseStatus.errorMessage)
        }
        
    }
    
    private let domainMapper: (BaseResponse<CryptoCurrencyBean>) -> [CryptoCurrencyModel] = { response in
        var result = [CryptoCurrencyModel]()
        response.data.forEach { data in
            let quote = data.quote.quoteInUSD
            let model = CryptoCurrencyModel(id: data.id, name: data.name, symbol: data.symbol, currentPrice: quote.currentPrice, percentChangeByHour: quote.percentChangeByHour, percentChangeByDay: quote.percentChangeByDay, percentChangeByWeek: quote.percentChangeByWeek, percentChangeByMonth: quote.percentChangeByMonth, totalCapitalization: quote.totalCapitalization, updatedDate: quote.updatedDate)
            
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
