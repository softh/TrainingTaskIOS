package by.st.kmm.sdk.data.network.exception

import by.st.kmm.sdk.data.network.exception.HttpException

/**
 * Thrown if the server returned 4xx status code
 *
 * @author RamashkaAE
 */
class InvalidClientRequestException(httpStatusCode: Int) : HttpException(httpStatusCode)