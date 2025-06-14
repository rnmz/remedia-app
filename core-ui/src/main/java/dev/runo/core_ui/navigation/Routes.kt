package dev.runo.core_ui.navigation

import kotlinx.serialization.Serializable

interface Route

data object BottomNavRoute {
    @Serializable
    data object Home : Route

    @Serializable
    data object Search : Route
}

data object TitleRoute {
    @Serializable
    data class Info(val id: Int) : Route

    @Serializable
    data class Reader(val id: Int) : Route
}

data object NewsRoute : Route {
    @Serializable
    data class Open(val id: Int) : Route
}