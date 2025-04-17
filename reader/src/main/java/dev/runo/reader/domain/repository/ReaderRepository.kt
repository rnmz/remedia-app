package dev.runo.reader.domain.repository

import dev.runo.core.common.WorkStatus
import dev.runo.reader.domain.model.Chapter

interface ReaderRepository {
    suspend fun getAllChapters(titleId: Int): WorkStatus<List<Chapter>>
    suspend fun getImagesByChapterId(chapterId: Int): WorkStatus<List<String>>
}