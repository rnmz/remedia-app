package dev.runo.core.network.title

import com.google.gson.annotations.SerializedName


data class TitleListModel(
    val titles: List<TitleModel>,
    @SerializedName("all_pages")
    val allPages: Int,
    @SerializedName("current_page")
    val currentPage: Int
)