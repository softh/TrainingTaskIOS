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

    func loadCryptocurrenciesList(_ listSize: Int = 50) {
        postStartOperation()
        CryptoSDKRxWrapper(sdk: self.sdk).getCryptoCurrenciesList(countOfItems: listSize).subscribeOn(SerialDispatchQueueScheduler.init(qos: .background))
            .observeOn(MainScheduler())
            .subscribe(onSuccess: loadListSuccessConsumer, onError: baseErrorConsumer)
            .disposed(by: disposeBag)
    }

    private func loadListSuccessConsumer(value: [CryptoCurrencyModel]) {
        postFinishOperation()
        currenciesListSubject.onNext(value)
    }
}
