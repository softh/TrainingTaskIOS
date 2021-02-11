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
    internal typealias T = BaseResponse<CryptoCurrencyBean>
    
    private let apiUrl: String
    private let apiToken: String
    
    init(apiUrl: String, apiToken: String) {
        self.apiUrl = apiUrl
        self.apiToken = apiToken
    }
    
    func getCryptoCurrenciesList(countOfItems: Int) -> Single<BaseResponse<CryptoCurrencyBean>>{
        return RxAlamofire.request(.get, apiUrl, headers: getHeaders(apiToken: apiToken))
            .validate()
            .responseJSON()
            .map {response in try! JSONDecoder().decode(BaseResponse<CryptoCurrencyBean>.self, from: response.data!) }
            
            .asSingle()
    }
    
    private func getHeaders(apiToken: String) -> [String: String] {
        return [apiTokenHeaderKey: apiToken]
    }
}
