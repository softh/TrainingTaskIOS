//
//  HostViewController.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 21.01.21.
//

import UIKit

class HostViewController: UITabBarController {

    override func viewDidLoad() {
        super.viewDidLoad()
        
        let listViewController = UINavigationController(rootViewController: ListViewController())
        let converterViewController = ConverterViewController()
    
        
        let listItem = UITabBarItem()
        listItem.title = NSLocalizedString("currencies_list_tab_label", comment: "")
        listItem.image = UIImage(named: "home_icon")
        listViewController.tabBarItem = listItem
        
        let converterItem = UITabBarItem()
        converterItem.title = NSLocalizedString("currencies_converter_tab_label", comment: "")
        converterItem.image = UIImage(named: "home_icon")
        converterViewController.tabBarItem = converterItem
        
        self.viewControllers = [listViewController, converterViewController]
        self.selectedViewController = listViewController
    }

}
