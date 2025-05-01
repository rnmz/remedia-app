package dev.runo.reader.data

import dev.runo.core.common.WorkStatus
import dev.runo.core.network.FileApi
import dev.runo.core.network.title.TitleApi
import dev.runo.core.network.utils.downloadImage
import dev.runo.core.network.utils.safeRequest
import dev.runo.reader.data.map.ConvertChapterModel
import dev.runo.reader.domain.model.Chapter
import dev.runo.reader.domain.repository.ReaderRepository
import javax.inject.Inject

class DefaultReaderRepository @Inject constructor(
    private val titleApi: TitleApi,
    private val fileApi: FileApi
) : ReaderRepository {

    override suspend fun getAllChapters(titleId: Int): WorkStatus<List<Chapter>> {
        return safeRequest(
            request = {
                titleApi.getTitleInfo(titleId)
            },
            onSuccessBlock = {
                WorkStatus.Success(
                    it.chapters.map { ConvertChapterModel.toDomain(it) }
                )
            },
            onErrorBlock = {
                WorkStatus.Error(it)
            }
        )
    }

    override suspend fun getImagesByChapterId(chapterId: Int): WorkStatus<List<ByteArray>> {
        val request = safeRequest(
            request = {
                titleApi.getChapter(chapterId)
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
                val images = request.data.fileIds.mapNotNull { fileId ->
                    downloadImage(fileApi, fileId, request.data.serverId)
                }
                WorkStatus.Success(images)
            }
            is WorkStatus.Error -> request
        }
    }

}