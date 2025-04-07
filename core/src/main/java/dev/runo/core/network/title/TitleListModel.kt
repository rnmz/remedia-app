package dev.runo.core.network.title


data class TitleListModel(
    val titles: List<TitleModel>,
    val allPages: Int,
    val currentPage: Int
)