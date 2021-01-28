//
//  CryptoCurrencyDataSource.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 21.01.21.
//

import Foundation
import RxSwift

protocol CryptoCurrencyDataSource {
    associatedtype T
    
    func getCryptoCurrenciesList(countOfItems: Int) -> Single<BaseResponse<CryptoCurrencyBean>>
}
