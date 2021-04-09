package by.st.kmm.sdk.data.currency

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