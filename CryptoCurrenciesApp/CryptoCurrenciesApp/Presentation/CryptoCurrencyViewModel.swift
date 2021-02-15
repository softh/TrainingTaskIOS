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
    
    private let cryptoCurrencyRepository : CryptoCurrencyRepository
    
    init(repository : CryptoCurrencyRepository) {
        self.cryptoCurrencyRepository = repository
    }
    
    func loadCryptocurrenciesList(_ listSize: Int = 50) {
        postStartOperation()
        let disposable = cryptoCurrencyRepository.getCryptoCurrenciesList(countOfItems: listSize)
            .observeOn(MainScheduler())
            .subscribeOn(SerialDispatchQueueScheduler.init(qos: .background))
            .subscribe(onSuccess: loadListSuccessConsumer, onError: self.baseErrorConsumer)
        
        _ = self.compositeDisposable.insert(disposable)
    }
    
    private func loadListSuccessConsumer(value: [CryptoCurrencyModel]) {
        postFinishOperation()
        currenciesListSubject.onNext(value)
    }
}
