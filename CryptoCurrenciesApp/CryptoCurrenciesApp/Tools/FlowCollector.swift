//
//  FlowCollector.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 12.04.21.
//

import Foundation
import CryptoCurrencySDK

class FlowCollector<T>: Kotlinx_coroutines_coreFlowCollector {

    let callback:(T) -> Void

    init(callback: @escaping (T) -> Void) {
        self.callback = callback
    }


    func emit(value: Any?, completionHandler: @escaping (KotlinUnit?, Error?) -> Void) {
        callback(value as! T)
        completionHandler(KotlinUnit(), nil)
    }
}

extension Kotlinx_coroutines_coreFlowCollector {
   
}
