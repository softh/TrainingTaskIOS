//
//  CurrencyModel.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 28.01.21.
//

import Foundation

struct CryptoCurrencyModelOld {
    let id: Int
    let name: String
    let symbol: String
    let currentPrice: Double
    let percentChangeByHour: Double
    let percentChangeByDay: Double
    let percentChangeByWeek: Double
    let percentChangeByMonth: Double
    let totalCapitalization: Double
    let updatedDate: Date
    
    init(id: Int, name: String, symbol: String, currentPrice: Double, percentChangeByHour: Double,
         percentChangeByDay: Double, percentChangeByWeek: Double, percentChangeByMonth: Double,
         totalCapitalization: Double, updatedDate: String) {
        
        self.id = id
        self.name = name
        self.symbol = symbol
        self.currentPrice = currentPrice
        self.percentChangeByHour = percentChangeByHour
        self.percentChangeByDay = percentChangeByDay
        self.percentChangeByWeek = percentChangeByWeek
        self.percentChangeByMonth = percentChangeByMonth
        self.totalCapitalization = totalCapitalization
        self.updatedDate = String.toDate(updatedDate)
    }
}

fileprivate extension String {
    static func toDate(_ value: String) -> Date {
        let dateFormatter = DateFormatter()
        dateFormatter.locale = Locale(identifier: "en_US_POSIX")
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z"
        return dateFormatter.date(from: value)!
    }
}
