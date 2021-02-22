//
//  CurrencyTableViewCell.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 22.02.21.
//

import UIKit

class CurrencyTableViewCell: UITableViewCell {

    @IBOutlet weak var nameVIew: UILabel!
    
    @IBOutlet weak var costView: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
}
