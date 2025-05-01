package dev.runo.search.data

import dev.runo.core.common.WorkStatus
import dev.runo.core.network.FileApi
import dev.runo.core.network.title.TitleApi
import dev.runo.core.network.title.TitleType
import dev.runo.core.network.utils.downloadImage
import dev.runo.core.network.utils.safeRequest
import dev.runo.search.data.map.ConvertTitleModel
import dev.runo.search.domain.model.TitleList
import dev.runo.search.domain.repository.SearchRepository
import javax.inject.Inject

class DefaultSearchRepository @Inject constructor(
    private val titleApi: TitleApi,
    private val fileApi: FileApi
) : SearchRepository {

    override suspend fun searchTitle(
        query: String?,
        genres: List<String>?,
        tags: List<String>?,
        years: List<Int>?,
        author: String?,
        titleType: TitleType?,
        orderType: Boolean?,
        page: Int
    ): WorkStatus<TitleList> {
        val request = safeRequest(
            request = {
                titleApi.searchByFilters(
                    name = query,
                    genres = genres,
                    tags = tags,
                    years = years,
                    author = author,
                    titleType = titleType?.type ?: TitleType.ALL.type, // do NOT touch.
                    orderType = orderType ?: true, // do NOT touch.
                    page = page
                )
            },
            onSuccessBlock = {
                WorkStatus.Success(it)
            },
            onErrorBlock = {
                WorkStatus.Error(it)
            }
        )

        return when (request) {
            is WorkStatus.Success -> {
                val convertedTitles = request.data.titles.mapNotNull { model ->
                    downloadImage(fileApi, model.image, model.serverId)?.let {
                        ConvertTitleModel.toDomain(model, it)
                    }
                }
                WorkStatus.Success(TitleList(convertedTitles, request.data.allPages))
            }
            is WorkStatus.Error -> request
        }
    }

    override suspend fun getPopularTitles(page: Int): WorkStatus<TitleList> {
        val request = safeRequest(
            request = {
                titleApi.getPopularTitles(page)
            },
            onSuccessBlock = {
                WorkStatus.Success(it)
            },
            onErrorBlock = {
                WorkStatus.Error(it)
            }
        )

        return when (request) {
            is WorkStatus.Success -> {
                val convertedTitles = request.data.titles.mapNotNull { model ->
                    downloadImage(fileApi, model.image, model.serverId)?.let {
                        ConvertTitleModel.toDomain(model, it)
                    }
                }
                WorkStatus.Success(TitleList(convertedTitles, request.data.allPages))
            }
            is WorkStatus.Error -> request
        }
    }

}