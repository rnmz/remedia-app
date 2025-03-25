package dev.runo.search.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.runo.core.navigation.AppNavGraphBuilder
import dev.runo.core.navigation.SearchRoute
import dev.runo.search.ui.SearchScreen
import javax.inject.Inject

class SearchGraph @Inject constructor() : AppNavGraphBuilder {
    override fun navGraph(): NavGraphBuilder.() -> Unit = {
        composable<SearchRoute> {
            SearchScreen()
        }
    }
}