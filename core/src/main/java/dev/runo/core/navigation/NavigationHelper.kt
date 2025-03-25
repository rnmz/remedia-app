package dev.runo.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.createGraph
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationHelper @Inject constructor(private val routes: Set<@JvmSuppressWildcards AppNavGraphBuilder>) {

    fun createNavGraph(navController: NavController): NavGraph {
        return navController.createGraph(
            startDestination = HomeRoute
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