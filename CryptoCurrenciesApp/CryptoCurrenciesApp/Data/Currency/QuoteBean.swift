
import Foundation

/**

 */
struct QuoteBean : Decodable {
    let quoteInUSD: CurrencyBean
    
    enum CodingKeys: String, CodingKey {
        case quoteInUSD = "USD"
    }
}

struct CurrencyBean : Decodable {
    let currentPrice: Double
    let percentChangeByHour: Double
    let percentChangeByDay: Double
    let percentChangeByWeek: Double
    let percentChangeByMonth: Double
    let totalCapitalization: Double
    let updatedDate: String
    
    enum CodingKeys: String, CodingKey {
        case currentPrice = "price"
        case percentChangeByHour = "percent_change_1h"
        case percentChangeByDay = "percent_change_24h"
        case percentChangeByWeek = "percent_change_7d"
        case percentChangeByMonth = "percent_change_30d"
        case totalCapitalization = "market_cap"
        case updatedDate = "last_updated"
    }
}
