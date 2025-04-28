package dev.runo.core.network.title

import com.google.gson.annotations.SerializedName

data class ChapterModel(
    val id: Int,
    @SerializedName("publish_date")
    val publishDate: String,
    @SerializedName("book_number")
    val bookNumber: Int,
    @SerializedName("chapter_id")
    val chapterNumber: Float,
    val name: String
)