package dev.runo.title.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import dev.runo.core_ui.navigation.AppNavGraphBuilder
import dev.runo.title.ui.navigation.TitleGraph

@Module
@InstallIn(SingletonComponent::class)
abstract class TitleModule {
    @Binds
    @IntoSet
    abstract fun bindTitleGraph(titleGraph: TitleGraph): AppNavGraphBuilder
}