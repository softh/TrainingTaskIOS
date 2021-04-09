package by.st.kmm.sdk.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author RamshkaAE
 */
@Serializable
data class BaseResponse<T>(

    @SerialName("data")
    val data: List<T>,

    @SerialName("responseStatus")
    val responseStatus: ResponseStatusDto
)