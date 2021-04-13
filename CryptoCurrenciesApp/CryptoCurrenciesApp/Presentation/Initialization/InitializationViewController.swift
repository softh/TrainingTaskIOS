//
//  InitializationViewController.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 13.04.21.
//
private let maxItems = 30

import UIKit
import CryptoCurrencySDK
import Lottie
import RxSwift

class InitializationViewController: UIViewController {
    
    @IBOutlet weak var progressView: UILabel!
    @IBOutlet weak var animationView: AnimationView!
    
    private let disposeBag = DisposeBag()
    
    var viewModel = InitializationViewModel(sdk: SDKProvider.getSDK())
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        progressView.text = "preparing_data_label".localized
        viewModel.initializationProgressSubject.subscribe(InitializationProgressObserver(self)).disposed(by: disposeBag)
        viewModel.initializationCompleteSubject.subscribe(InitializationCompleteObserver(self)).disposed(by: disposeBag)
        playAnimation()
        viewModel.startInitialization(poolSize: SDKProvider.defaultCurrencyListSize)
        
    }
    
    private func playAnimation() {
        let animation = Animation.named("init")
        animationView.animation = animation
        animationView.contentMode = .scaleAspectFit
        animationView.loopMode = .loop
        animationView.play()
    }
    
    private class InitializationCompleteObserver : ObserverType {
        typealias Element = Bool
        
        private weak var controllerReference: InitializationViewController?

        init(_ controllerReference: InitializationViewController) {
            self.controllerReference = controllerReference
        }
        
        func on(_ event: Event<Bool>) {
            let sceneDelegate = controllerReference?.view.window?.windowScene?.delegate
            if let delegate = sceneDelegate {
                let castedDelegate = delegate as! SceneDelegate
                castedDelegate.showHostViewController()
            }
        }
    }
    
    private class InitializationProgressObserver: ObserverType {
        typealias Element = Int

        private weak var controllerReference: InitializationViewController?

        init(_ controllerReference: InitializationViewController) {
            self.controllerReference = controllerReference
        }

        func on(_ event: Event<Int>) {
            controllerReference?.progressView.text = "\(String(describing: event.element!))%"
        }
    }
    
}


