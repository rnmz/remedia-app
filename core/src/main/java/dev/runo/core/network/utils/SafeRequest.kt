package dev.runo.core.network.utils

import dev.runo.core.common.AppError
import dev.runo.core.network.error.NetworkErrorList
import okio.IOException
import retrofit2.Response
import java.net.SocketTimeoutException

suspend fun <INPUT, OUTPUT> safeRequest(
    request: suspend () -> Response<INPUT>,
    onSuccessBlock: (INPUT) -> OUTPUT,
    onErrorBlock: (type: AppError) -> OUTPUT,
    errorMapper: ((Response<*>) -> AppError)? = null
): OUTPUT {
    return try {
        val response = request()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                onSuccessBlock(body)
            }
            else {
                onErrorBlock(NetworkErrorList.UNEXPECTED)
            }
        }
        else {
            val errorType = errorMapper?.invoke(response) ?: when (response.code()) {
                401 -> NetworkErrorList.UNAUTHORIZED
                403 -> NetworkErrorList.FORBIDDEN
                404 -> NetworkErrorList.NOT_FOUND
                451 -> NetworkErrorList.LEGAL_REASON
                in 500..599 -> NetworkErrorList.INTERNAL_SERVER
                else -> NetworkErrorList.UNEXPECTED
            }
            onErrorBlock(errorType)
        }
    }
    catch (_: SocketTimeoutException) {
        onErrorBlock(NetworkErrorList.TIMEOUT)
    }
    catch (_: IOException) {
        onErrorBlock(NetworkErrorList.NO_CONNECTION)
    }
    catch (_: Exception) {
        onErrorBlock(NetworkErrorList.UNEXPECTED)
    }
}