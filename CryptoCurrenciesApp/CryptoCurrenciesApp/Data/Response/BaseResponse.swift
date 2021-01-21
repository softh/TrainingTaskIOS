//
//  BaseResponse.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 21.01.21.
//

import Foundation
import Alamofire
import ObjectMapper

class BaseResponse : Mappable {
    
    private(set) var responseStatus: ResponseStatusBean? = nil
    
    func mapping(map: Map) {
        self.responseStatus <- map["status"]
    }
    
    required init?(map: Map) {
        
    }

}
