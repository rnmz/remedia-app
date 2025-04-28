package dev.runo.core.network.title

import com.google.gson.annotations.SerializedName

data class ChapterImageModel(
    @SerializedName("file_id")
    val fileIds: List<String>,
    @SerializedName("server_id")
    val serverId: Int
)
