package dev.runo.core.network.news

data class NewsListModel(
    val news: List<NewsModel>,
    val allPages: Int,
    val currentPage: Int
)
