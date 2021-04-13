package by.st.kmm.sdk.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author RamshkaAE
 */
@Serializable
data class BaseMapResponse<T>(
    @SerialName("data")
    val data: Map<String, T>?,

    @SerialName("status")
    val responseStatus: ResponseStatusDto
)
