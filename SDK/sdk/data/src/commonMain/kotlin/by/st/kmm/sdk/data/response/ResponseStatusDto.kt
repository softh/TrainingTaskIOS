package by.st.kmm.sdk.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author RamashkaAE
 */
@Serializable
data class ResponseStatusDto(

        @SerialName("timeStamp")
        val timeStamp: String,

        @SerialName("errorCode")
        val errorCode: Int,

        @SerialName("errorMessage")
        val errorMessage: String?
)