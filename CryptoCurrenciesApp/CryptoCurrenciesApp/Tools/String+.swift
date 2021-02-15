//
//  String+.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 16.02.21.
//

import Foundation

extension String {
    var localized: String {
        return NSLocalizedString(self, tableName: nil, bundle: Bundle.main, value: "", comment: "")
    }
}
