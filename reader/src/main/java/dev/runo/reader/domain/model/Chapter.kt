package dev.runo.reader.domain.model

import java.time.LocalDate

data class Chapter(
    val id: Int,
    val publishDate: LocalDate,
    val bookNumber: Int,
    val chapterNumber: Float,
    val name: String
)
