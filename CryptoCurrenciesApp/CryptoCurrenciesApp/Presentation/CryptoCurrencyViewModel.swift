//
//  CryptoCurrencyViewModel.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 11.02.21.
//

import Foundation
import RxSwift

class CryptoCurrencyViewModel: BaseViewModel {

    private(set) lazy var currenciesListSubject = PublishSubject<[CryptoCurrencyModel]>()

    private let cryptoCurrencyRepository: CryptoCurrencyRepository

    init(repository: CryptoCurrencyRepository) {
        cryptoCurrencyRepository = repository
    }

    func loadCryptocurrenciesList(_ listSize: Int = 50) {
        postStartOperation()
        cryptoCurrencyRepository.getCryptoCurrenciesList(countOfItems: listSize)
                .subscribeOn(SerialDispatchQueueScheduler.init(qos: .background))
                .observeOn(MainScheduler())
                .subscribe(onSuccess: loadListSuccessConsumer, onError: baseErrorConsumer)
                .disposed(by: disposeBag)
    }

    private func loadListSuccessConsumer(value: [CryptoCurrencyModel]) {
        postFinishOperation()
        currenciesListSubject.onNext(value)
    }
}
