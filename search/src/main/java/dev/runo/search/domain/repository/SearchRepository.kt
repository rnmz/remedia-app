package dev.runo.search.domain.repository

import dev.runo.core.common.WorkStatus
import dev.runo.core.network.title.TitleType
import dev.runo.search.domain.model.TitleList

interface SearchRepository {

    suspend fun searchTitle(
        query: String?,
        genres: List<String>?,
        tags: List<String>?,
        years: List<Int>?,
        author: String?,
        titleType: TitleType?,
        orderType: Boolean?,
        page: Int
    ): WorkStatus<TitleList>

    suspend fun getPopularTitles(page: Int): WorkStatus<TitleList>

}