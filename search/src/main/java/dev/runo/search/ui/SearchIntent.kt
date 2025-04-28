package dev.runo.search.ui

sealed interface SearchIntent {

    data class SearchByFilters(
        val query: String?,
        val genres: List<String>?,
        val tags: List<String>?,
        val yearFrom: Int?,
        val yearTo: Int?,
        val author: String?,
        val titleType: Int,
        val orderType: Boolean?,
        val page: Int
    ) : SearchIntent

}