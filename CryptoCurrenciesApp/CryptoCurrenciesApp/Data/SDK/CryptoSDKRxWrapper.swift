//
//  CryptoSDKRxWrapper.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 10.04.21.
//

import Foundation
import RxSwift
import CryptoCurrencySDK

class CryptoSDKRxWrapper {
    
    static func fromSDK(sdkProtocol: CryptoSDKProtocol) -> CryptoSDKRxWrapper {
        return CryptoSDKRxWrapper(sdk: sdkProtocol)
    }
    
    private let sdk : CryptoSDKProtocol
    
    init(sdk: CryptoSDKProtocol) {
        self.sdk = sdk
    }
    
    func getCryptoCurrenciesList(countOfItems: Int) -> Single<[CryptoCurrencyModel]> {
        return Single<[CryptoCurrencyModel]>.create(subscribe: { event in
            self.sdk.getCryptoCurrenciesList(countOfItems: Int32(countOfItems), completionHandler: {models, error in
                if let listOfItems = models {
                    event(.success(listOfItems))
                }
                
                if let sdkError = error {
                    event(.error(sdkError))
                }
            })
            return Disposables.create()
        })
    }
}

extension CryptoSDKProtocol {
    func rx() -> CryptoSDKRxWrapper {
        return CryptoSDKRxWrapper.fromSDK(sdkProtocol: self)
    }
}

extension Single {
    static func fromCompletionHandler<T>(_ completiomHandler: (T?, Error?)) -> Single<T> {
        return Single<T>.create(subscribe: {event in
            if let result = completiomHandler.0 {
                event(.success(result))
            }
            
            if let sdkError = completiomHandler.1 {
                event(.error(sdkError))
            }
            
            return Disposables.create()
        })
    }
}

class Collector<T>: Kotlinx_coroutines_coreFlowCollector {

    let callback:(T) -> Void

    init(callback: @escaping (T) -> Void) {
        self.callback = callback
    }


    func emit(value: Any?, completionHandler: @escaping (KotlinUnit?, Error?) -> Void) {
        // do whatever you what with the emitted value
        callback(value as! T)

        // after you finished your work you need to call completionHandler to
        // tell that you consumed the value and the next value can be consumed,
        // otherwise you will not receive the next value
        //
        // i think first parameter can be always nil or KotlinUnit()
        // second parameter is for an error which occurred while consuming the value
        // pass an error object will throw a NSGenericException in kotlin code, which can be handled or your app will crash
        completionHandler(KotlinUnit(), nil)
    }
}
