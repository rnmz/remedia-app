package dev.runo.home.data

import dev.runo.core.common.WorkStatus
import dev.runo.core.network.news.NewsApi
import dev.runo.core.network.utils.safeRequest
import dev.runo.core.network.title.TitleApi
import dev.runo.home.data.map.ConvertNewsModel
import dev.runo.home.data.map.ConvertTitleModel
import dev.runo.home.domain.model.News
import dev.runo.home.domain.model.Title
import dev.runo.home.domain.repository.HomeRepository
import javax.inject.Inject

class DefaultHomeRepository @Inject constructor(
    private val titleApi: TitleApi,
    private val newsApi: NewsApi
) : HomeRepository {

    override suspend fun getPopularTitles(): WorkStatus<List<Title>> {
        return safeRequest(
            request = {
                titleApi.getPopularTitles(1)
            },
            onSuccessBlock = {
                WorkStatus.Success(it.titles.map { ConvertTitleModel.toDomain(it) })
            },
            onErrorBlock = {
                WorkStatus.Error(it)
            }
        )
    }

    override suspend fun getLatestNews(): WorkStatus<List<News>> {
        return safeRequest(
            request = {
                newsApi.getLatestNews(1)
            },
            onSuccessBlock = {
                WorkStatus.Success(it.news.map { ConvertNewsModel.toDomain(it) })
            },
            onErrorBlock = {
                WorkStatus.Error(it)
            }
        )
    }

}