package by.st.kmm.sdk.data.network.exception

/**
 * Thrown if the server returned 5xx status code
 *
 * @author RamashkaAE
 */
class ServerInternalErrorException(httpStatusCode: Int) : HttpException(httpStatusCode)