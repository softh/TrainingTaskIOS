package by.st.kmm.sdk.data.common.net

/**
 * Thrown if the server returned 5xx status code
 *
 * @author RamashkaAE
 */
class ServerInternalErrorException(httpStatusCode: Int) : HttpException(httpStatusCode)