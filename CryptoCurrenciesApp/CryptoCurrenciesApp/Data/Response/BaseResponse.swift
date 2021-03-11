//
//  BaseResponse.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 21.01.21.
//

import Foundation
import Alamofire
import ObjectMapper

struct BaseResponse<T : Decodable> : Decodable {
    let data: [T]
    let responseStatus: ResponseStatusBean
    
    enum CodingKeys: String, CodingKey {
        case data = "data"
        case responseStatus = "status"
    }

}
