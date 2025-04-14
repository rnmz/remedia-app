package dev.runo.home.domain.repository

import dev.runo.core.common.WorkStatus
import dev.runo.home.domain.model.News
import dev.runo.home.domain.model.Title

interface HomeRepository {
    suspend fun getPopularTitles(): WorkStatus<List<Title>>
    suspend fun getLatestNews(): WorkStatus<List<News>>
}