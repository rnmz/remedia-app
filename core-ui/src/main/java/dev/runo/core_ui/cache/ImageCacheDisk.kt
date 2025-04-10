package dev.runo.core_ui.cache

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ImageCacheDisk(private val context: Context) {

    private val cacheDir = context.cacheDir

    fun saveToDisk(key: String, bitmap: Bitmap) {
        val file = File(cacheDir, key)

        try {
            FileOutputStream(file).use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun getFromDisk(key: String): Bitmap? {
        val file = File(cacheDir, key)
        return if (file.exists()) {
            BitmapFactory.decodeFile(file.absolutePath)
        }
        else {
            null
        }
    }
}