package dev.runo.core_ui.cache

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import dev.runo.core_ui.UI_LOG_TAG
import javax.inject.Inject

class ImageLoader @Inject constructor(private val context: Context) {

    private val ramCache = ImageCacheRAM()
    private val diskCache = ImageCacheDisk(context)

    fun loadImage(key: String): Bitmap? {
        ramCache.getFromRam(key)?.let {
            Log.d(UI_LOG_TAG, "[Cache] image loaded from memory.")
            return it
        }

        diskCache.getFromDisk(key)?.let {
            Log.d(UI_LOG_TAG, "[Cache] image loaded from disk.")
            ramCache.addImage(key, it)
            return it
        }

        return null
    }

    fun saveImage(key: String, bitmap: Bitmap) {
        ramCache.addImage(key, bitmap)
        diskCache.saveToDisk(key, bitmap)
        Log.d(UI_LOG_TAG, "[Cache] image $key saved in cache")
    }
}