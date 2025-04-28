package dev.runo.core.network.news

import com.google.gson.annotations.SerializedName

data class NewsModel(
    val id: Int,
    val title: String,
    val author: String,
    val content: String,
    @SerializedName("publish_date")
    val publishDate: String
)