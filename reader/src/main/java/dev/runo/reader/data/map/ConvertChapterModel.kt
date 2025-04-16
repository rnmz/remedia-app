package dev.runo.reader.data.map

import dev.runo.core.network.title.ChapterModel
import dev.runo.reader.domain.model.Chapter
import java.time.LocalDate

object ConvertChapterModel {
    fun toDomain(chapterModel: ChapterModel): Chapter {
        return Chapter(
            id = chapterModel.id,
            publishDate = LocalDate.parse(chapterModel.publishDate),
            bookNumber = chapterModel.bookNumber,
            chapterNumber = chapterModel.chapterNumber,
            name = chapterModel.name
        )
    }
}