package by.st.kmm.sdk.data.currency

import by.st.kmm.sdk.domain.currency.CryptoCurrencyModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author RamashkaAE
 */
@Serializable
data class CryptoCurrencyDto(

    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("symbol")
    val symbol: String,

    @SerialName("quote")
    val quote: QuoteDto
)

internal fun CryptoCurrencyDto.toDomainModel() : CryptoCurrencyModel {
    return CryptoCurrencyModel(
        this.id,
        this.name,
        this.symbol,
        this.quote.quoteInUSD.currentPrice,
        this.quote.quoteInUSD.percentChangeByHour,
        this.quote.quoteInUSD.percentChangeByDay,
        this.quote.quoteInUSD.percentChangeByWeek,
        this.quote.quoteInUSD.percentChangeByMonth,
        this.quote.quoteInUSD.totalCapitalization,
        this.quote.quoteInUSD.updatedDate
    )
}

internal fun List<CryptoCurrencyDto>?.toDomainModel() : List<CryptoCurrencyModel> {
    return this?.map { it.toDomainModel() } ?: listOf()
}