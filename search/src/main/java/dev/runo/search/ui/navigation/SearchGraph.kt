package dev.runo.search.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.runo.core_ui.navigation.AppNavGraphBuilder
import dev.runo.core_ui.navigation.AppRoute
import dev.runo.search.ui.screen.SearchScreen
import javax.inject.Inject

class SearchGraph @Inject constructor() : AppNavGraphBuilder {
    override fun navGraph(): NavGraphBuilder.() -> Unit = {
        composable<AppRoute.Search> {
            SearchScreen()
        }
    }
}