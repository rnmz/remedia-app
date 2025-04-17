package dev.runo.reader.data

import dev.runo.core.common.WorkStatus
import dev.runo.core.network.safeRequest
import dev.runo.core.network.title.TitleApi
import dev.runo.reader.data.map.ConvertChapterModel
import dev.runo.reader.domain.model.Chapter
import dev.runo.reader.domain.repository.ReaderRepository

class DefaultReaderRepository(
    private val titleApi: TitleApi
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

    override suspend fun getImagesByChapterId(chapterId: Int): WorkStatus<List<String>> {
        return safeRequest(
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
    }

}