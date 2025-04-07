package dev.runo.core.network.title

data class TitleModel(
    val id: Int,
    val image: String,
    val name: String,
    val rating: Float,
    val chapters: List<ChapterModel>
)