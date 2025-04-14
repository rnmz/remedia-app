package dev.runo.home.domain.model

import java.time.LocalDateTime

data class News(
    val id: Int,
    val title: String,
    val publishDate: LocalDateTime,
    val content: String
)