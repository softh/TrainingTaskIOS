//
//  DetailsViewController.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 22.02.21.
//

import UIKit
import CryptoCurrencySDK

class DetailsViewController: UIViewController {

    private let model: CryptoCurrencyModel

    init(model: CryptoCurrencyModel) {
        self.model = model
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) is not supported")
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        title = model.name
    }
}
