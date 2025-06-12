package dev.runo.core.network.title

import com.google.gson.annotations.SerializedName

data class TitleModel(
    val id: Int,
    val image: String,
    val name: String,
    val rating: Float,
    @SerializedName("server_id")
    val serverId: Int,
    val genres: List<String>,
    val chapters: List<ChapterModel>
)