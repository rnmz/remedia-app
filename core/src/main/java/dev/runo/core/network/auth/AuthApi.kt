package dev.runo.core.network.auth

import dev.runo.core.common.RELEASE_AUTH_DOMAIN
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface AuthApi {

    /**
     * Update Refresh and Access tokens.
     * This API may return HTTP 401 if the refresh token is missing or invalid.
     * @param refresh Refresh token.
     * @return [TokensModel]. Contains **new** refresh and access token.
     */
    @POST
    fun updateRefreshToken(
        @Url url: String = RELEASE_AUTH_DOMAIN,
        @Body refresh: String
    ): Response<TokensModel>

    /**
     * Update Access token.
     * This API may return HTTP 401 if the refresh token is missing or invalid.
     * @param refresh Refresh token.
     * @return New access token as a [String].
     */
    @POST
    fun updateAccessToken(
        @Url url: String = RELEASE_AUTH_DOMAIN,
        @Body refresh: String
    ): Response<String>

}