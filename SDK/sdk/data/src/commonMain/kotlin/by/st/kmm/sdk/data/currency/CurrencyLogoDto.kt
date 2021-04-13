package by.st.kmm.sdk.data.currency

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyLogoDto(
    @SerialName("id")
    val id: Int,
    @SerialName("logo")
    val logoUrl: String
)