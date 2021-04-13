package by.st.kmm.sdk.data.common.net

import io.ktor.client.*

/**
 * Provides support methods to create http client via platform and trust policy
 *
 * @author RamashkaAE
 */
internal expect object HttpClientProvider {

    fun getNewHttpClientInstance(trustAllCertificates: Boolean) : HttpClient
}