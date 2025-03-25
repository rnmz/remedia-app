package dev.runo.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
object SearchRoute

@Serializable
data class TitleRoute(val titleId: Long)

@Serializable
data class ReaderRoute(val titleId: Long)