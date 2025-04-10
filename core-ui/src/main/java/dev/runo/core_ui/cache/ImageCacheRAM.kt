package dev.runo.core_ui.cache

import android.graphics.Bitmap
import android.util.LruCache

class ImageCacheRAM {
    private val lruCache = LruCache<String, Bitmap>(10)

    fun addImage(key: String, bitmap: Bitmap) {
        if (getFromRam(key) == null) {
            lruCache.put(key, bitmap)
        }
    }

    fun getFromRam(key: String): Bitmap? {
        return lruCache.get(key)
    }
}