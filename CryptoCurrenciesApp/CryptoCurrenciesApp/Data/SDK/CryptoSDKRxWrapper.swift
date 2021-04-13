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
