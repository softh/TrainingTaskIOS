//
//  ConverterViewModel.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 19.04.21.
//

import Foundation
import CryptoCurrencySDK
import RxSwift

class ConverterViewModel : BaseViewModel {
    private(set) lazy var currenciesListSubject = PublishSubject<[CryptoCurrencyModel]>()
    
    private let sdk: CryptoSDKProtocol
    
    init(sdk: CryptoSDKProtocol) {
        self.sdk = sdk
    }
    
    func loadCryptocurrenciesList(_ listSize: Int) {
        postStartOperation()
        sdk.rx().getCryptoCurrenciesList(countOfItems: listSize)
            .subscribe(onSuccess: loadListSuccessConsumer, onError: baseErrorConsumer)
            .disposed(by: disposeBag)
    }
    
    private func loadListSuccessConsumer(value: [CryptoCurrencyModel]) {
        postFinishOperation()
        currenciesListSubject.onNext(value)
    }
}
