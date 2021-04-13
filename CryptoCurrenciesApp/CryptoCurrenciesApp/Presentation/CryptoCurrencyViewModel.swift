//
//  CryptoCurrencyViewModel.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 11.02.21.
//

import Foundation
import RxSwift
import CryptoCurrencySDK

class CryptoCurrencyViewModel: BaseViewModel {
    
    private(set) lazy var currenciesListSubject = PublishSubject<[CryptoCurrencyModel]>()
    
    private let sdk: CryptoSDKProtocol
    
    init(sdk: CryptoSDKProtocol) {
        self.sdk = sdk
    }
    
    func emit(value: [CryptoCurrencyModel]) {
        
    }
    
    func loadCryptocurrenciesList(_ listSize: Int = 1500) {
        postStartOperation()
        
        CryptoSDKRxWrapper(sdk: self.sdk).getCryptoCurrenciesList(countOfItems: listSize)
            .subscribe(onSuccess: loadListSuccessConsumer, onError: baseErrorConsumer)
            .disposed(by: disposeBag)
    }
    
    private func loadListSuccessConsumer(value: [CryptoCurrencyModel]) {
        postFinishOperation()
        currenciesListSubject.onNext(value)
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
