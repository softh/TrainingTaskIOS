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
        
        let listViewController = ListViewController()
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


    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
