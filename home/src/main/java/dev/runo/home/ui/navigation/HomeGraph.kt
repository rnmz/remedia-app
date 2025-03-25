package dev.runo.home.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.runo.core.navigation.AppNavGraphBuilder
import dev.runo.core.navigation.HomeRoute
import dev.runo.home.ui.HomeScreen
import javax.inject.Inject

class HomeGraph @Inject constructor() : AppNavGraphBuilder {
    override fun navGraph(): NavGraphBuilder.() -> Unit = {
        composable<HomeRoute> {
            HomeScreen()
        }
    }
}