package dev.runo.reader.data

import dev.runo.core.common.WorkStatus
import dev.runo.core.network.error.NetworkErrorList
import dev.runo.core.network.title.TitleApi
import dev.runo.reader.data.map.ConvertChapterModel
import dev.runo.reader.domain.model.Chapter
import dev.runo.reader.domain.repository.ReaderRepository
import okio.IOException
import java.net.SocketTimeoutException

// Yeah, I know, shitty code. Idk what I should to do.
class DefaultReaderRepository(
    private val titleApi: TitleApi
) : ReaderRepository {

    override suspend fun getAllChapters(titleId: Long): WorkStatus<List<Chapter>> {
        return try {
            val request = titleApi.getTitleInfo(titleId)
            if (request.isSuccessful) {
                WorkStatus.Success(
                    request.body()!!.chapters.map { ConvertChapterModel.toDomain(it) }
                )
            } else {
                if (request.code() == 401) {
                    WorkStatus.Error(NetworkErrorList.UNAUTHORIZED)
                }
                else if (request.code() == 403) {
                    WorkStatus.Error(NetworkErrorList.FORBIDDEN)
                }
                else if (request.code() == 500) {
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

    override suspend fun getImagesByChapterId(chapterId: Int): WorkStatus<List<String>> {
        return try {
            val request = titleApi.getChapter(chapterId)
            if (request.isSuccessful) {
                WorkStatus.Success(
                    request.body()!!
                )
            } else {
                if (request.code() == 401) {
                    WorkStatus.Error(NetworkErrorList.UNAUTHORIZED)
                }
                else if (request.code() == 403) {
                    WorkStatus.Error(NetworkErrorList.FORBIDDEN)
                }
                else if (request.code() == 500) {
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