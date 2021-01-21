//
//  CryptoCurrencyNetworkSource.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 21.01.21.
//

import Foundation
import RxSwift
import RxAlamofire
import Alamofire
import ObjectMapper

private let apiTokenHeaderKey = "X-CMC_PRO_API_KEY"

class CryptoCurrencyNetworkSource : CryptoCurrencyDataSource {
    
    typealias T = Int
    
    
    private let apiUrl: String
    private let apiToken: String
    
    init(apiUrl: String, apiToken: String) {
        self.apiUrl = apiUrl
        self.apiToken = apiToken
    }
    
    func getCryptoCurrenciesList(countOfItems: Int) -> Single<BaseResponse> {
        return json(.get, apiUrl, headers: getHeaders(apiToken: apiToken))
            .mapObject(type: BaseResponse.self)
            .asSingle()
    }

    
    private func getHeaders(apiToken: String) -> [String: String] {
        return [apiTokenHeaderKey: apiToken]
    }
}
