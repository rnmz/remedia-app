package dev.runo.search.ui.screen

data class TitlesListUiState(
    val titles: List<TitleUiState>? = null,
    val allPages: Int = 1,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
