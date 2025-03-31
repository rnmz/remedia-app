package dev.runo.core_ui.navigation

import kotlinx.serialization.Serializable

data object AppRoute {
    @Serializable
    data object Home

    @Serializable
    data object Search
}

data object TitleRoute {
    @Serializable
    data object Info

    @Serializable
    data object Reader
}