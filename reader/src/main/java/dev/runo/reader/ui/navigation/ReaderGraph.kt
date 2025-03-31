package dev.runo.reader.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.runo.core_ui.navigation.AppNavGraphBuilder
import dev.runo.core_ui.navigation.TitleRoute
import dev.runo.reader.ui.ReaderScreen
import javax.inject.Inject

class ReaderGraph @Inject constructor() : AppNavGraphBuilder {
    override fun navGraph(): NavGraphBuilder.() -> Unit = {
        composable<TitleRoute.Reader> {
            ReaderScreen()
        }
    }
}