package dev.runo.core.network.utils

import dev.runo.core.network.FileApi
import java.io.ByteArrayOutputStream

suspend fun downloadImage(
    fileApi: FileApi,
    fileId: String,
    serverId: Int
): ByteArray? {
    val request = fileApi.downloadFileFromCDN("image", serverId, fileId)
    val inputStream = request.body()?.byteStream()
    val outputStream = ByteArrayOutputStream()

    val buffer = ByteArray(16 * 1024) // 16 KB
    var bytesRead: Int

    inputStream?.use {
        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            outputStream.write(buffer, 0, bytesRead)
        }
    }
    outputStream.flush()
    return outputStream.toByteArray()
}