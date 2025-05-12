package dev.runo.core.network.file

import android.util.Log
import dev.runo.core.common.NETWORK_LOG_TAG
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
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

    try {
        inputStream?.use {
            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                coroutineContext.ensureActive()
                outputStream.write(buffer, 0, bytesRead)
            }
        }
        outputStream.flush()
        outputStream.toByteArray()
    }
    catch (_: CancellationException) {
        Log.d(NETWORK_LOG_TAG, "[GET] Image download cancelled. Image id: ${fileId}")
        null
    }
}