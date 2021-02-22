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

class ListViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {

    let cellId = "currencyCell"

    var cryptoCurrencies = [CryptoCurrencyModel]()

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return cryptoCurrencies.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell: CurrencyTableViewCell = tableView.dequeueReusableCell(withIdentifier: cellId)
                as! CurrencyTableViewCell

        let currency = cryptoCurrencies[indexPath.row]
        cell.nameVIew?.text = currency.name
        cell.costView?.text = String(currency.currentPrice)
        return cell
    }


    private var refreshControl = UIRefreshControl()
    @IBOutlet weak var tableView: UITableView!
    private(set) lazy var repository = CryptoCurrencyRepositoryImplementation(
            networkDataSource: CryptoCurrencyNetworkSource(apiUrl: url, apiToken: token), cacheDataSource: CryptoCurrencyCacheDataSource()
    )
    private(set) lazy var viewModel = CryptoCurrencyViewModel(repository: repository)

    override func viewDidAppear(_ animated: Bool) {
        initView()
        loadData()
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        tableView.register(UINib.init(nibName:"CurrencyTableViewCell", bundle: nil), forCellReuseIdentifier: cellId)
        tableView.delegate = self
        tableView.dataSource = self
    }

    private func initView() {
        view.backgroundColor = .red
        title = "currencies_list_tab_label".localized

        refreshControl.addTarget(self, action: #selector(refresh(_:)), for: .valueChanged)
        tableView.addSubview(refreshControl)

        viewModel.currenciesListSubject.subscribe(LoadDataObserver(self))
        viewModel.errorSubject.subscribe(ErrorObserver(self))
        viewModel.progressSubject.subscribe(ProgressObserver(self))
    }

    @objc func refresh(_ sender: AnyObject) {
        loadData()
    }

    func loadData() {
        viewModel.loadCryptocurrenciesList()
    }

    func successConsumer(response: [CryptoCurrencyModel]) {
        print(response.count)
    }

    override func viewDidDisappear(_ animated: Bool) {
        viewModel.destroy()
    }

    private class ErrorObserver: ObserverType {
        typealias Element = Error

        private weak var controllerReference: UIViewController?

        init(_ controllerReference: UIViewController) {
            self.controllerReference = controllerReference
        }

        func on(_ event: Event<Error>) {
            let alert = UIAlertController(title: "Error", message: event.element?.localizedDescription, preferredStyle: UIAlertController.Style.alert)
            alert.addAction(UIAlertAction(title: "OK", style: .destructive, handler: { _ in alert.dismiss(animated: true, completion: nil) }))
            controllerReference?.present(alert, animated: true, completion: nil)
        }
    }

    private class LoadDataObserver: ObserverType {
        typealias Element = [CryptoCurrencyModel]

        private weak var controllerReference: ListViewController?

        init(_ controllerReference: ListViewController) {
            self.controllerReference = controllerReference
        }

        func on(_ event: Event<[CryptoCurrencyModel]>) {
            controllerReference?.tableView.refreshControl = nil
            if event.element != nil {
                controllerReference?.cryptoCurrencies.removeAll()
                controllerReference?.cryptoCurrencies += event.element ?? []
                controllerReference?.tableView.reloadData()
            }
        }
    }
}

private class ProgressObserver: ObserverType {

    typealias Element = BaseViewModel.OperationStatus

    private weak var controllerReference: UIViewController?
    private var progressView: ProgressView?

    init(_ controllerReference: UIViewController) {
        self.controllerReference = controllerReference
        progressView = ProgressView((self.controllerReference?.view)!)
    }

    func on(_ event: Event<BaseViewModel.OperationStatus>) {
        showProgress(show: event.element == BaseViewModel.OperationStatus.inProgress ||
                event.element == BaseViewModel.OperationStatus.started)
    }

    private func showProgress(show: Bool) {
        if (show) {
            progressView?.show()
        } else {
            progressView?.dismiss()
        }
    }

}
