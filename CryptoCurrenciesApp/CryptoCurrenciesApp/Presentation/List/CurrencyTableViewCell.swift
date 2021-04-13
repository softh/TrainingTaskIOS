//
//  CurrencyTableViewCell.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 22.02.21.
//

import UIKit
import CryptoCurrencySDK

class CurrencyTableViewCell: UITableViewCell {

    @IBOutlet weak var nameView: UILabel!
    
    @IBOutlet weak var costView: UILabel!
    
    @IBOutlet weak var logoView: UIImageView!
    
    var currencyModel: CryptoCurrencyModel? {
        didSet {
            if let model = currencyModel {
                nameView.text = model.name
                costView.text = "$\(model.currentPrice.roundedToPlaces())"
                if let imageData = model.logoData {
                    logoView.image = UIImage(data: imageData.toData())
                }
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
