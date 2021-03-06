package by.st.kmm.sdk.data.common.net

import by.st.kmm.sdk.data.common.net.HttpException

/**
 * Thrown if the server returned 4xx status code
 *
 * @author RamashkaAE
 */
class InvalidClientRequestException(httpStatusCode: Int) : HttpException(httpStatusCode)