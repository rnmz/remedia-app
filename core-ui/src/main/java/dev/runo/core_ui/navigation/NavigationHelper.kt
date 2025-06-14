package dev.runo.core_ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.createGraph
import javax.inject.Inject


class NavigationHelper @Inject constructor(private val routes: Set<@JvmSuppressWildcards AppNavGraphBuilder>) {
    fun createNavGraph(navController: NavController): NavGraph {
        return navController.createGraph(
            startDestination = BottomNavRoute.Home
        ) {
            routes.forEach {
                it.navGraph().invoke(this)
            }
        }
    }
}

fun interface AppNavGraphBuilder {
    fun navGraph(): NavGraphBuilder.() -> Unit
}