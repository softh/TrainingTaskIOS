//
//  CryptoCurrencyRepository.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 28.01.21.
//

import Foundation
import RxSwift

protocol CryptoCurrencyRepository {
    func getCryptoCurrenciesList(countOfItems: Int) -> Single<Array<CryptoCurrencyModel>>
}
