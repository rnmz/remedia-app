package dev.runo.home.data.map

import dev.runo.core.network.title.TitleModel
import dev.runo.home.domain.model.Title

object ConvertTitleModel {
    fun toDomain(titleModel: TitleModel): Title {
        return Title(
            id = titleModel.id,
            image = titleModel.image,
            name = titleModel.name,
            rating = titleModel.rating
        )
    }
}