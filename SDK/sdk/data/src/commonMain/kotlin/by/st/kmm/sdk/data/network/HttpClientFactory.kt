package by.st.kmm.sdk.data.network

import by.st.kmm.sdk.data.network.HttpClientProvider
import by.st.kmm.sdk.data.network.exception.InvalidClientRequestException
import by.st.kmm.sdk.data.network.exception.RedirectRequestException
import by.st.kmm.sdk.data.network.exception.ServerInternalErrorException
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

private const val API_TOKEN_HEADER_KEY = "X-CMC_PRO_API_KEY"

/**
 * Creates platform-specific [HttpClient]
 *
 * @author RamashkaAE
 */
internal object HttpClientFactory {

    /**
     * Creates base platform-specific [HttpClient] with default config and map of transport exceptions
     */
    fun createHttpClient(apiToken: String, trustAllCertificates: Boolean = false, isLogEnabled: Boolean): HttpClient {
        return HttpClientProvider.getNewHttpClientInstance(trustAllCertificates).config {
            install(JsonFeature) {
                val json = Json { ignoreUnknownKeys = true }
                serializer = KotlinxSerializer(json)
            }

            if (isLogEnabled) {
                install(Logging) {
                    logger = Logger.SIMPLE
                    level = LogLevel.BODY
                }
            }

            defaultRequest {
                header(API_TOKEN_HEADER_KEY, apiToken)
            }

            HttpResponseValidator {
                validateResponse { response ->
                    val statusCode = response.status.value
                    when (statusCode) {
                        in 300..399 -> throw RedirectRequestException(response.status.value)
                        in 400..499 -> throw InvalidClientRequestException(response.status.value)
                        in 500..599 -> throw ServerInternalErrorException(response.status.value)
                    }

                    if (statusCode >= 600) {
                        throw ResponseException(response)
                    }
                }
            }
        }
    }
}