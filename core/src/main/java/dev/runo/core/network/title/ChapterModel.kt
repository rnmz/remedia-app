package dev.runo.core.network.title

import java.time.LocalDate

data class ChapterModel(
    val id: Int,
    val publishDate: LocalDate,
    val bookNumber: Int,
    val chapterNumber: Float,
    val name: String
)