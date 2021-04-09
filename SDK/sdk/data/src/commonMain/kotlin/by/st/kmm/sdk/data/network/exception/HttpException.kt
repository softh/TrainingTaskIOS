package by.st.kmm.sdk.data.network.exception

private const val ERROR_MESSAGE_TEMPLATE = "Server returns error code: "

/**
 * Thrown if the server returned http status code other than 2xx
 *
 * @author RamashkaAE
 */
abstract class HttpException(httpStatusCode: Int) : Exception("$ERROR_MESSAGE_TEMPLATE$httpStatusCode")