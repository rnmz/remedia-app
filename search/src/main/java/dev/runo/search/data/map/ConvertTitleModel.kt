package dev.runo.search.data.map

import dev.runo.core.network.title.TitleModel
import dev.runo.search.domain.model.Title

object ConvertTitleModel {
    fun toDomain(titleModel: TitleModel, img: ByteArray): Title {
        return Title(
            id = titleModel.id,
            image = img,
            name = titleModel.name,
            rating = titleModel.rating
        )
    }
}