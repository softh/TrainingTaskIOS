package by.st.kmm.sdk.domain.currency

/**
 * @author RamashkaAE
 */
data class CryptoCurrencyModel(
    val id: Int,
    val name: String,
    val symbol: String,
    val currentPrice: Double,
    val percentChangeByHour: Double,
    val percentChangeByDay: Double,
    val percentChangeByWeek: Double,
    val percentChangeByMonth: Double,
    val totalCapitalization: Double,
    val updatedDate: String,
    val logoData: ByteArray?
)