package by.st.kmm.sdk.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author RamashkaAE
 */
@Serializable
data class ResponseStatusDto(

        @SerialName("timestamp")
        val timeStamp: String,

        @SerialName("error_code")
        val errorCode: Int,

        @SerialName("error_message")
        val errorMessage: String?
)