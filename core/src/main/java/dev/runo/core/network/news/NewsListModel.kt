package dev.runo.core.network.news

import com.google.gson.annotations.SerializedName

data class NewsListModel(
    @SerializedName("items")
    val news: List<NewsModel>,
    @SerializedName("all_pages")
    val allPages: Int,
    @SerializedName("current_page")
    val currentPage: Int
)
