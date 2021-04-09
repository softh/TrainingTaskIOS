package by.st.kmm.sdk.data.currency

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author RamshkaAE
 */
@Serializable
data class QuoteDto(

    @SerialName("USD")
    val quoteInUSD: CurrencyDto
)

@Serializable
data class CurrencyDto(

    @SerialName("price")
    val currentPrice: Double,

    @SerialName("percent_change_1h")
    val percentChangeByHour: Double,

    @SerialName("percent_change_24h")
    val percentChangeByDay: Double,

    @SerialName("percent_change_7d")
    val percentChangeByWeek: Double,

    @SerialName("percent_change_30d")
    val percentChangeByMonth: Double,

    @SerialName("market_cap")
    val totalCapitalization: Double,

    @SerialName("last_updated")
    val updatedDate: String
)