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
    
    func convert(sourceCurrencyId: Int, targetCurrencyId: Int) -> Single<Double> {
        return Single<Double>.create(subscribe: { event in
            self.sdk.convert(sourceCurrencyId: Int32(sourceCurrencyId), targetCurrencyId: Int32(targetCurrencyId), completionHandler: {convertedPrice, error in
                if let value = convertedPrice {
                    event(.success(value.doubleValue))
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
