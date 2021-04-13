//
//  DetailsViewController.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 22.02.21.
//

import UIKit
import CryptoCurrencySDK

class DetailsViewController: UIViewController {

    @IBOutlet weak var logoView: UIImageView!
    
    @IBOutlet weak var nameView: UILabel!
    
    @IBOutlet weak var symbolView: UILabel!
    
    @IBOutlet weak var currentPriceView: UILabel!
    
    @IBOutlet weak var totalCapitalizationView: UILabel!
    
    
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
        if let imageData = model.logoData {
            logoView.image = UIImage(data: imageData.toData())
        }
        
        nameView.text = model.name
        symbolView.text = model.symbol
        currentPriceView.text = "\("currency_crypto_current_price_label".localized)\(model.currentPrice)\("currency_usd_symbol".localized)"
        totalCapitalizationView.text = "\("currency_crypto_capitalization_label".localized)\(model.totalCapitalization)\("currency_usd_symbol".localized)"
    }
}
