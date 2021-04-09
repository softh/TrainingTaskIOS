package by.st.kmm.sdk.data.network.exception

/**
 * Thrown if the server returned 3xx status code
 *
 * @author RamashkaAE
 */
class RedirectRequestException(httpStatusCode: Int) : HttpException(httpStatusCode)