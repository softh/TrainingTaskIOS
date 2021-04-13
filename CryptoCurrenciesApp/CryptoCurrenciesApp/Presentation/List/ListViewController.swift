//
//  ListViewController.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 21.01.21.
//

import UIKit
import RxSwift
import RxCocoa
import CryptoCurrencySDK

class ListViewController: UIViewController {

    let disposeBag = DisposeBag()

    let cellId = "currencyCell"

    var refreshControl = UIRefreshControl()
    @IBOutlet weak var tableView: UITableView!
    
    
    private(set) lazy var viewModel = CryptoCurrencyListViewModel(sdk: createSDK())
    
    private func createSDK() -> CryptoSDKProtocol {
        var sdkInstance: CryptoSDKProtocol
          do {
            sdkInstance = try CryptoSDKBuilder()
            .withApiEndpoint(apiEndpoint: "https://pro-api.coinmarketcap.com/v1")
            .withApiToken(apiToken: "3032f753-b744-4448-9cf1-bf6ae80dbb7c")
                .withCachingType(cachingType: CachingType.inMemory)
                .enableLogging(enable: true)
                .withDatabaseDriverFactory(databaseDriverFactory: DatabaseDriverFactory())
                .withCacheLifeTime(milliseconds: 10000)
            .build()
            
            return sdkInstance
        } catch {
            exit(-1)
        }
        
        return sdkInstance
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        
        initView()
        loadData()
    }

    private func initView() {
        title = "currencies_list_tab_label".localized

        refreshControl.addTarget(self, action: #selector(refresh(_:)), for: .valueChanged)
        tableView.addSubview(refreshControl)

        navigationItem.rightBarButtonItem = UIBarButtonItem.init(barButtonSystemItem: UIBarButtonItem.SystemItem.refresh,
                target: self, action: #selector(refresh(_:)))

        viewModel.errorSubject.subscribe(ErrorObserver(self)).disposed(by: disposeBag)
        viewModel.progressSubject.subscribe(ProgressObserver(self)).disposed(by: disposeBag)

        initTableView()
    }

    private func initTableView() {
        tableView.register(UINib.init(nibName: "CurrencyTableViewCell", bundle: nil), forCellReuseIdentifier: cellId)

        tableView.rx.modelSelected(CryptoCurrencyModel.self)
                .subscribe(onNext: { model in
                    self.navigationController?.pushViewController(
                            DetailsViewController(model: model),
                            animated: true
                    )
                }).disposed(by: disposeBag)

        viewModel.currenciesListSubject.bind(to: tableView.rx.items(cellIdentifier: cellId,
                cellType: CurrencyTableViewCell.self)) { (row, item, cell) in
            cell.currencyModel = item
        }.disposed(by: disposeBag)
    }

    @objc func refresh(_ sender: AnyObject) {
        loadData()
    }

    func loadData() {
        viewModel.loadCryptocurrenciesList(20)
    }
    

    func createSingle<T>(
        scope: Kotlinx_coroutines_coreCoroutineScope,
        suspendWrapper: SuspendWrapper<T>
    ) -> Single<T> {
        return Single<T>.create { single in
            let job: Kotlinx_coroutines_coreJob = suspendWrapper.subscribe(
                scope: scope,
                onSuccess: { item in single(.success(item!)) },
                onThrow: { error in single(.error(KotlinError(error))) }
            )
            return Disposables.create { job.cancel(cause: nil) }
        }
    }
    
    class KotlinError: LocalizedError {
        let throwable: KotlinThrowable
        init(_ throwable: KotlinThrowable) {
            self.throwable = throwable
        }
        var errorDescription: String? {
            get { throwable.message }
        }
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
}

private class ProgressObserver: ObserverType {

    typealias Element = BaseViewModel.OperationStatus

    private weak var controllerReference: ListViewController?
    private var progressView: ProgressView?

    init(_ controllerReference: ListViewController) {
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
            controllerReference?.tableView.refreshControl = nil
        }
    }
    
}
