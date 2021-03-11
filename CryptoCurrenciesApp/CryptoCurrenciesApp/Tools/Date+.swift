//
//  Date+.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 8.02.21.
//

import Foundation

extension Date {
    var milliseconds:Int {
        return Int((self.timeIntervalSince1970 * 1000.0).rounded())
    }
}
