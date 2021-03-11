//
//  CurrencyTableViewCell.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 22.02.21.
//

import UIKit

class CurrencyTableViewCell: UITableViewCell {

    @IBOutlet private weak var nameView: UILabel!
    
    @IBOutlet private weak var costView: UILabel!

    var currencyModel: CryptoCurrencyModel? {
        didSet {
            if let model = currencyModel {
                nameView.text = model.name
                costView.text = "$\(model.currentPrice.roundedToPlaces())"
            }
        }
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
}
