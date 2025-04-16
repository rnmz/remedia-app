package dev.runo.core.network.title

data class ChapterModel(
    val id: Int,
    val publishDate: String,
    val bookNumber: Int,
    val chapterNumber: Float,
    val name: String
)