package dev.runo.core.network.file

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

suspend fun downloadImage(
    fileApi: FileApi,
    fileId: String,
    serverId: Int
): ByteArray? = withContext(Dispatchers.IO) {
    val request = fileApi.downloadFileFromCDN("image", serverId, fileId)
    val inputStream = request.body()?.byteStream()
    val outputStream = ByteArrayOutputStream()

    val buffer = ByteArray(16 * 1024) // 16 KB
    var bytesRead: Int

    inputStream?.use {
        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            if (!coroutineContext.isActive) return@withContext null
            outputStream.write(buffer, 0, bytesRead)
        }
    }
    outputStream.flush()
    outputStream.toByteArray()
}