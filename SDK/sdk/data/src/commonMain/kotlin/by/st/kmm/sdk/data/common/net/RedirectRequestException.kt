package by.st.kmm.sdk.data.common.net

import by.st.kmm.sdk.data.common.net.HttpException

/**
 * Thrown if the server returned 3xx status code
 *
 * @author RamashkaAE
 */
class RedirectRequestException(httpStatusCode: Int) : HttpException(httpStatusCode)