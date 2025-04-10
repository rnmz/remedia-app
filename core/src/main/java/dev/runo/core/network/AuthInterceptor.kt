package dev.runo.core.network

import android.util.Log
import dev.runo.core.DataStoreManager
import dev.runo.core.common.NETWORK_LOG_TAG
import dev.runo.core.network.annotation.OptionalApiKey
import dev.runo.core.network.annotation.RequiresApiKey
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Invocation
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataStoreManager: DataStoreManager
): Interceptor {
    
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val method = request.method()
        val endpoint = request.url().encodedPath()
        val apiKey = dataStoreManager.getApiKey()

        val optionalApiKey = request.tag(Invocation::class.java)?.method()?.getAnnotation(OptionalApiKey::class.java)
        val requiresApiKey = request.tag(Invocation::class.java)?.method()?.getAnnotation(RequiresApiKey::class.java)

        return when {
            optionalApiKey != null -> handleOptionalApiKey(request, apiKey, method, endpoint, chain)
            requiresApiKey != null -> handleRequiredApiKey(request, apiKey, method, endpoint, chain)
            else -> {
                Log.d(NETWORK_LOG_TAG, "[$method] $endpoint does not requiring an API key.")
                chain.proceed(request)
            }
        }
    }

    private fun handleOptionalApiKey(
        request: Request,
        apiKey: String,
        method: String,
        endpoint: String,
        chain: Interceptor.Chain
    ): Response {
        return if (apiKey.isNotBlank()) {
                Log.d(NETWORK_LOG_TAG, "[$method] $endpoint was executed successfully with an API key.")
                val newRequest = request.newBuilder()
                    .addHeader("Authorization", "Bearer $apiKey")
                    .build()
                chain.proceed(newRequest)
            }
            else {
                Log.d(NETWORK_LOG_TAG, "[$method] $endpoint was executed successfully without an API key.")
                chain.proceed(request)
            }
    }

    private fun handleRequiredApiKey(
        request: Request,
        apiKey: String,
        method: String,
        endpoint: String,
        chain: Interceptor.Chain
    ): Response {
        return if (apiKey.isNotBlank()) {
            Log.d(NETWORK_LOG_TAG, "[$method] $endpoint required an API key and was executed successfully.")
            val newRequest = request.newBuilder()
                .addHeader("Authorization", "Bearer $apiKey")
                .build()
            chain.proceed(newRequest)
        }
        else {
            Log.w(NETWORK_LOG_TAG, "[$method] $endpoint not executed: API key is required but was null or empty.")
            Response.Builder()
                .code(401) // HTTP 401 - Unauthorized
                .message("Missing API key")
                .protocol(Protocol.HTTP_1_1)
                .request(request)
                .body(
                    ResponseBody.create(
                        MediaType.parse("application/json"),
                        "{\"error\": \"Missing API key\"}"
                    )
                )
                .build()
        }
    }

}
