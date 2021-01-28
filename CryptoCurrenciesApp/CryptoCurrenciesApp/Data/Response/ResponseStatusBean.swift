//
//  ResponseStatus.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 21.01.21.
//

import Foundation

struct ResponseStatusBean : Decodable {
    let timeStamp: String
    let errorCode: Int
    let errorMessage: String?
    
    enum CodingKeys: String, CodingKey {
        case timeStamp = "timestamp"
        case errorCode = "error_code"
        case errorMessage = "error_message"
    }
}
