package dev.runo.core.network.file

import dev.runo.core.network.annotation.OptionalApiKey
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FileApi {

    /**
     * Download file from CDN as [okhttp3.ResponseBody].
     * This API may return [HTTP 401] or [HTTP 403] if the user doesn't have the necessary permissions.
     *
     * @param type File Type. It can be image, pdf or gif.
     * @param serverId Server id.
     * @param fileId File id.
     * @return the file as a [ByteArray].
     */
    @GET("/cdn/download/")
    @OptionalApiKey
    suspend fun downloadFileFromCDN(
        @Query("file_type") type: String,
        @Query("server_id") serverId: Int,
        @Query("file_id") fileId: String
    ): Response<ResponseBody>

}