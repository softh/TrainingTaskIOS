//
//  SdkProvider.swift
//  CryptoCurrenciesApp
//
//  Created by Алексей Рамашка on 14.04.21.
//

import Foundation
import CryptoCurrencySDK

class SDKProvider {
    
    static let defaultCurrencyListSize = 50
    
    private static var sdkInstance: CryptoSDKProtocol?
    
    static func getSDK() -> CryptoSDKProtocol {
        if(sdkInstance == nil) {
            sdkInstance = createSDK()
            
        }
        return sdkInstance!
    }
    
    private static func createSDK() -> CryptoSDKProtocol {
        var sdkInstance: CryptoSDKProtocol
        do {
            sdkInstance = try CryptoSDKBuilder()
                .withApiEndpoint(apiEndpoint: "https://pro-api.coinmarketcap.com/v1")
                .withApiToken(apiToken: "3032f753-b744-4448-9cf1-bf6ae80dbb7c")
                .withDatabaseDriverFactory(databaseDriverFactory: DatabaseDriverFactory())
                .enableLogging(enable: true)
                .withCachingType(cachingType: .inMemory)
                .withCacheLifeTime(milliseconds: 20000)
                .build()
            
            return sdkInstance
        } catch {
            fatalError("SDK not initialized")
        }
    }
    
    
}
