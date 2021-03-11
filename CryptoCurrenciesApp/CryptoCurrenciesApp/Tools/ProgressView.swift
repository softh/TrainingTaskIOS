//
//  ProgressView.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 15.02.21.
//

import Foundation
import UIKit

class ProgressView {
    private let indicatorSize = 70
    private weak var rootView: UIView?
    private let backgroundAlphaView: CGFloat = 0.75
    private lazy var backGroundView: UIView? = getBackGroundView()
    
    init(_ rootView: UIView) {
        self.rootView = rootView
    }
    
    private func getBackGroundView() -> UIView? {
        if let view = rootView {
            let backGroundView = UIView(frame: CGRect(x: 0, y: 0, width: view.bounds.width, height: view.bounds.height))
            backGroundView.backgroundColor = UIColor.black
            backGroundView.alpha = backgroundAlphaView
            let progressView = UIActivityIndicatorView(style: .medium)
            progressView.frame = CGRect(x: 0, y: 0, width: indicatorSize, height: indicatorSize)
            progressView.center = backGroundView.center
            progressView.startAnimating()
            backGroundView.addSubview(progressView)
            
            return backGroundView
        } else {
            return nil
        }
        
    }
    
    func show() {
        if let view = rootView {
            if let background = backGroundView {
                view.window?.addSubview(background)
            }
        }
    }
    
    func dismiss() {
        if let background = backGroundView {
            background.removeFromSuperview()
        }
    }
}
