package dev.runo.home.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.runo.core_ui.navigation.AppNavGraphBuilder
import dev.runo.core_ui.navigation.AppRoute
import javax.inject.Inject

class HomeGraph @Inject constructor() : AppNavGraphBuilder {
    override fun navGraph(): NavGraphBuilder.() -> Unit = {
        composable<AppRoute.Home> {
        }
    }
}