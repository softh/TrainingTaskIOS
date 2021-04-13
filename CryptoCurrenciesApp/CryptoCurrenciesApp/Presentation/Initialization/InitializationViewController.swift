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
    
    @IBOutlet weak var ProgressView: UILabel!
    @IBOutlet weak var animationView: AnimationView!
    
    private let disposeBag = DisposeBag()
    
    private(set) lazy var viewModel = InitializationViewModel(sdk: createSDK())
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        viewModel.initializationProgressSubject.subscribe(InitializationProgressObserver(self)).disposed(by: disposeBag)
        viewModel.initializationCompleteSubject.subscribe(InitializationCompleteObserver(self)).disposed(by: disposeBag)
        playAnimation()
        viewModel.startInitialization(poolSize: 20)
        
    }
    
    private func playAnimation() {
        let animation = Animation.named("init")
        animationView.animation = animation
        animationView.contentMode = .scaleAspectFit
        animationView.loopMode = .loop
        animationView.play()
    }
    
    private func createSDK() -> CryptoSDKProtocol {
        var sdkInstance: CryptoSDKProtocol
        do {
            sdkInstance = try CryptoSDKBuilder()
                .withApiEndpoint(apiEndpoint: "https://pro-api.coinmarketcap.com/v1")
                .withApiToken(apiToken: "3032f753-b744-4448-9cf1-bf6ae80dbb7c")
                .withDatabaseDriverFactory(databaseDriverFactory: DatabaseDriverFactory())
                .enableLogging(enable: true)
                .build()
            
            return sdkInstance
        } catch {
            exit(-1)
        }
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
            controllerReference?.ProgressView.text = "\(String(describing: event.element!))%"
        }
    }
    
}


