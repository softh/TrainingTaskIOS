//
//  ResponseStatus.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 21.01.21.
//

import Foundation
import ObjectMapper

class ResponseStatusBean : Mappable {
    private(set) var timeStamp: String = ""
    private(set) var errorCode: Int = 0
    private(set) var errorMessage: String = ""
    
    required init?(map: Map) {
      
    }
    
    func mapping(map: Map) {
        timeStamp <- map["timestamp"]
        errorCode <- map["errorcode"]
        errorMessage <- map["errormessage"]
    }
}
