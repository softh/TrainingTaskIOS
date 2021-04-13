//
//  InitializationViewModel.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 13.04.21.
//

import Foundation
import RxSwift
import CryptoCurrencySDK

class InitializationViewModel : BaseViewModel {
    private(set) lazy var initializationProgressSubject = PublishSubject<Int>()
    private(set) lazy var initializationCompleteSubject = PublishSubject<Bool>()
    
    private let sdk: CryptoSDKProtocol
    
    init(sdk: CryptoSDKProtocol) {
        self.sdk = sdk
    }
    
    func startInitialization(poolSize: Int) {
        sdk.startInitialization(countOfItems: Int32(poolSize), initializationProgressListener: Listener(initializationProgressSubject, poolSize), completionHandler_: { [self]models, error in
            if let sdkError = error {
                self.postOperationError(sdkError)
            } else {
                self.initializationCompleteSubject.onNext(true)
            }
        })
    }
}

fileprivate class Listener : InitializationProgressListener {
    private weak var progressSubject: PublishSubject<Int>?
    private let poolSize: Int
    
    init(_ progressSubject: PublishSubject<Int>, _ poolSize: Int) {
        self.progressSubject = progressSubject
        self.poolSize = poolSize
    }
    
    func onProgress(currentStep: Int32) {
        progressSubject?.onNext(Int(((Double(currentStep)/Double(poolSize)) * 100)))
    }
}
