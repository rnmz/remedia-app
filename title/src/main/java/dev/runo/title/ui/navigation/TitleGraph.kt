package dev.runo.title.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dev.runo.core_ui.navigation.AppNavGraphBuilder
import dev.runo.core_ui.navigation.TitleRoute
import javax.inject.Inject

class TitleGraph @Inject constructor() : AppNavGraphBuilder {
    override fun navGraph(): NavGraphBuilder.() -> Unit = {
        composable<TitleRoute.Info> { backStack ->
            val data = backStack.toRoute<TitleRoute>()
        }
    }
}