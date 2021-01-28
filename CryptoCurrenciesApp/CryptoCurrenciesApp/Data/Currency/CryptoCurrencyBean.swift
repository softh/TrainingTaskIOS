//
//  CryptoCurrencyBean.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 28.01.21.
//

import Foundation

struct CryptoCurrencyBean : Decodable {
    let id: Int
    let name: String
    let symbol: String
    let quote: QuoteBean
    
    enum CodingKeys: String, CodingKey {
        case id
        case name
        case symbol
        case quote
    }
}
