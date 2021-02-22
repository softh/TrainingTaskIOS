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

    let disposeBag = DisposeBag()

    let cellId = "currencyCell"

    var cryptoCurrencies = [CryptoCurrencyModel]()

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        cryptoCurrencies.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell: CurrencyTableViewCell = tableView.dequeueReusableCell(withIdentifier: cellId)
                as! CurrencyTableViewCell

        let currency = cryptoCurrencies[indexPath.row]
        cell.nameVIew?.text = currency.name
        cell.costView?.text = "$\(currency.currentPrice.roundedToPlaces())"
        return cell
    }

    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        showDetails(model: cryptoCurrencies[indexPath.row])
    }

    private func showDetails(model: CryptoCurrencyModel) {
        let targetController = DetailsViewController(model: model)
        targetController.view.backgroundColor = UIColor.red
        navigationController?.pushViewController(targetController, animated:
        true)
    }

    private var refreshControl = UIRefreshControl()
    @IBOutlet weak var tableView: UITableView!
    private(set) lazy var repository = CryptoCurrencyRepositoryImplementation(
            networkDataSource: CryptoCurrencyNetworkSource(apiUrl: url, apiToken: token), cacheDataSource: CryptoCurrencyCacheDataSource()
    )
    private(set) lazy var viewModel = CryptoCurrencyViewModel(repository: repository)

    override func viewDidLoad() {
        super.viewDidLoad()
        initView()
        loadData()
    }

    private func initView() {
        view.backgroundColor = .red
        title = "currencies_list_tab_label".localized

        tableView.register(UINib.init(nibName: "CurrencyTableViewCell", bundle: nil), forCellReuseIdentifier: cellId)
        tableView.delegate = self
        tableView.dataSource = self

        refreshControl.addTarget(self, action: #selector(refresh(_:)), for: .valueChanged)
        tableView.addSubview(refreshControl)

        navigationItem.rightBarButtonItem = UIBarButtonItem.init(barButtonSystemItem: UIBarButtonItem.SystemItem.refresh,
                target: self, action: #selector(refresh(_:)))

        viewModel.currenciesListSubject.subscribe(LoadDataObserver(self)).disposed(by: disposeBag)
        viewModel.errorSubject.subscribe(ErrorObserver(self)).disposed(by: disposeBag)
        viewModel.progressSubject.subscribe(ProgressObserver(self)).disposed(by: disposeBag)
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
