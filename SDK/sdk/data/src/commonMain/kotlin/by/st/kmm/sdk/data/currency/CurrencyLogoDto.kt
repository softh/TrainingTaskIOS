package by.st.kmm.sdk.data.currency

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyLogoDto(
    @SerialName("id")
    val currencyId: Long,
    @SerialName("logo")
    val logoUrl: String
)

data class CurrencyLogoPayload(
    val currencyId: Long,
    val logoPayload: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CurrencyLogoPayload

        if (currencyId != other.currencyId) return false
        if (!logoPayload.contentEquals(other.logoPayload)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = currencyId.hashCode()
        result = 31 * result + logoPayload.contentHashCode()
        return result
    }
}