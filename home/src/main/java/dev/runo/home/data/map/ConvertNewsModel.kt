package dev.runo.home.data.map

import dev.runo.core.network.news.NewsModel
import dev.runo.home.domain.model.News
import java.time.LocalDateTime

object ConvertNewsModel {
    fun toDomain(newsModel: NewsModel): News {
        return News(
            id = newsModel.id,
            title = newsModel.title,
            publishDate = LocalDateTime.parse(newsModel.publishDate),
            content = newsModel.content
        )
    }
}