package by.st.kmm.sdk.data.network

import io.ktor.client.*

/**
 * Provides support methods to create http client via platform and trust policy
 *
 * @author RamashkaAE
 */
internal expect object HttpClientProvider {

    fun getNewHttpClientInstance(trustAllCertificates: Boolean) : HttpClient
}