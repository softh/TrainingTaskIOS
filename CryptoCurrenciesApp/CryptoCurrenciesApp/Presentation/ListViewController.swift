//
//  ListViewController.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 21.01.21.
//

import UIKit
import RxSwift

class ListViewController: UIViewController {
    
    let url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?limit=50"
    let token = "07c16939-e6cc-4446-8053-283b35eb91fa"

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .red
        
        _ = CryptoCurrencyNetworkSource(apiUrl: url, apiToken: token)
            .getCryptoCurrenciesList(countOfItems: 50)
            .observeOn(MainScheduler())
            .subscribeOn(SerialDispatchQueueScheduler.init(qos: .background))
            .subscribe(onSuccess: successConsumer, onError: errorConsumer)
    }
    
    func successConsumer(response: BaseResponse) {
        let a = 0
    }
    
    func errorConsumer(error: Error) {
        let b = 0
    }
}
