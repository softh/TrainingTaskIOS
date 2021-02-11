//
//  ListViewController.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 21.01.21.
//

import UIKit
import RxSwift

let url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?limit=200"
let token = "07c16939-e6cc-4446-8053-283b35eb91fa"

class ListViewController: UIViewController {
    
    let compositDisposable = CompositeDisposable()
    
    let repository = CryptoCurrencyRepositoryImplementation(
        networkDataSource: CryptoCurrencyNetworkSource(apiUrl: url, apiToken: token), cacheDataSource: CryptoCurrencyCacheDataSource()
    )
    
    @IBAction func onLoadButtonEvent(_ sender: Any) {
        loadData()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .red
        title = NSLocalizedString("currencies_list_tab_label", comment: "")
        
        let refreshControl = UIRefreshControl()
        refreshControl.attributedTitle = NSAttributedString(string: "Pull to refresh")
        refreshControl.addTarget(self, action: #selector(self.refresh(_:)), for: .valueChanged)
        view.addSubview(refreshControl) // not required when using UITableViewController
    }
    
    @objc func refresh(_ sender: AnyObject) {
        loadData()
    }
    
    func loadData() {
        
        let disposable = repository.getCryptoCurrenciesList(countOfItems: 200)
            .observeOn(MainScheduler())
            .subscribeOn(SerialDispatchQueueScheduler.init(qos: .background))
            .subscribe(onSuccess: successConsumer, onError: errorConsumer)
        
        compositDisposable.insert(disposable)
    }
    
    func successConsumer(response: [CryptoCurrencyModel]) {
        print(response.count)
    }
    
    func errorConsumer(error: Error) {
        let alert = UIAlertController(title: "Error", message: error.localizedDescription, preferredStyle: UIAlertController.Style.alert)
        self.present(alert, animated: true, completion: nil)
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        compositDisposable.dispose()
    }
}
