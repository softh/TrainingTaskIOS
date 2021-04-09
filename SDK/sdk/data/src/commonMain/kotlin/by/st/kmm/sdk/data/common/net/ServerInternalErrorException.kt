package by.st.forms.sdk.data.common.net.exception

import by.st.kmm.sdk.data.common.net.HttpException

/**
 * Thrown if the server returned 5xx status code
 *
 * @author RamashkaAE
 */
class ServerInternalErrorException(httpStatusCode: Int) : HttpException(httpStatusCode)