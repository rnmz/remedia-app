package dev.runo.core.network.news

data class NewsModel(
    val id: Long,
    val title: String,
    val author: String,
    val content: String,
    val publishDate: String
)