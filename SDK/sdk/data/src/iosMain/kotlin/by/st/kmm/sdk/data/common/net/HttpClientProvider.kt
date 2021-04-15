package by.st.kmm.sdk.data.common.net

import io.ktor.client.*
import io.ktor.client.engine.ios.*

/**
 * @author RamashkaAE
 */
internal actual object HttpClientProvider {
    actual fun getNewHttpClientInstance(trustAllCertificates: Boolean): HttpClient {
        val engine = Ios.create {
            configureRequest {
                setAllowsCellularAccess(true)
            }
        }

        return HttpClient(engine)
    }
}