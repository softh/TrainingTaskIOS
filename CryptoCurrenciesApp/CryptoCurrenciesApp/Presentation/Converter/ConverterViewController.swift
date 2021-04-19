//
//  ConverterViewController.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 21.01.21.
//

import UIKit
import RxSwift
import RxCocoa

class ConverterViewController: UIViewController {

    private let disposeBag = DisposeBag()
    
    private(set) lazy var viewModel = ConverterViewModel(sdk: SDKProvider.getSDK())
    
    @IBOutlet weak var resultTextView: UILabel!
    
    @IBOutlet weak var valueInputView: UITextField!
    
    @IBOutlet weak var sourcePickerView: UIPickerView!
    
    private let editTextDelegate = EditTextDelegate()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        title = NSLocalizedString("currencies_converter_tab_label", comment: "")
        
        valueInputView.delegate = editTextDelegate
        initView()
        viewModel.loadCryptocurrenciesList(150)
    }
    
    private func initView() {
        viewModel.currenciesListSubject.bind(to: sourcePickerView.rx.items){ (row, element, view) in
            guard let view = view as? CurrencyTableViewCell else {
                let view = CurrencyTableViewCell()
                view.currencyModel = element
                return view
            }
            return view
        }
        .disposed(by: disposeBag)
    }
    
    
}

class EditTextDelegate : NSObject, UITextFieldDelegate {
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        let allowedCharacters = CharacterSet.decimalDigits
        let characterSet = CharacterSet(charactersIn: string)
        return allowedCharacters.isSuperset(of: characterSet)
    }
}
