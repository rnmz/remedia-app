package dev.runo.home.data

import dev.runo.core.common.WorkStatus
import dev.runo.core.network.error.NetworkErrorList
import dev.runo.core.network.news.NewsApi
import dev.runo.core.network.title.TitleApi
import dev.runo.home.data.map.ConvertNewsModel
import dev.runo.home.data.map.ConvertTitleModel
import dev.runo.home.domain.model.News
import dev.runo.home.domain.model.Title
import dev.runo.home.domain.repository.HomeRepository
import okio.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class DefaultHomeRepository @Inject constructor(
    private val titleApi: TitleApi,
    private val newsApi: NewsApi
) : HomeRepository {

    override suspend fun getPopularTitles(): WorkStatus<List<Title>> {
        return try {
            val request = titleApi.getPopularTitles(1)
            if (request.isSuccessful) {
               WorkStatus.Success(
                   request.body()!!.titles.map { ConvertTitleModel.toDomain(it) }
               )
            } else {
                if (request.code() == 500) {
                    WorkStatus.Error(NetworkErrorList.INTERNAL_SERVER)
                }
                else {
                    WorkStatus.Error(NetworkErrorList.UNEXPECTED)
                }
            }
        }
        catch (_: SocketTimeoutException) {
            WorkStatus.Error(NetworkErrorList.TIMEOUT)
        }
        catch (_: IOException) {
            WorkStatus.Error(NetworkErrorList.NO_CONNECTION)
        }
        catch (_: Exception) {
            WorkStatus.Error(NetworkErrorList.UNEXPECTED)
        }
    }

    override suspend fun getLatestNews(): WorkStatus<List<News>> {
        return try {
            val request = newsApi.getLatestNews(1)
            if (request.isSuccessful) {
                WorkStatus.Success(
                    request.body()!!.news.map { ConvertNewsModel.toDomain(it) }
                )
            } else {
                if (request.code() == 500) {
                    WorkStatus.Error(NetworkErrorList.INTERNAL_SERVER)
                }
                else {
                    WorkStatus.Error(NetworkErrorList.UNEXPECTED)
                }
            }
        }
        catch (_: SocketTimeoutException) {
            WorkStatus.Error(NetworkErrorList.TIMEOUT)
        }
        catch (_: IOException) {
            WorkStatus.Error(NetworkErrorList.NO_CONNECTION)
        }
        catch (_: Exception) {
            WorkStatus.Error(NetworkErrorList.UNEXPECTED)
        }
    }

}