package dev.runo.reader.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.runo.core.navigation.AppNavGraphBuilder
import dev.runo.core.navigation.ReaderRoute
import dev.runo.reader.ui.ReaderScreen
import javax.inject.Inject

class ReaderGraph @Inject constructor() : AppNavGraphBuilder {
    override fun navGraph(): NavGraphBuilder.() -> Unit = {
        composable<ReaderRoute> {
            ReaderScreen()
        }
    }
}