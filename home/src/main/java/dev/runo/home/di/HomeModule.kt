package dev.runo.home.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import dev.runo.core_ui.navigation.AppNavGraphBuilder
import dev.runo.home.ui.navigation.HomeGraph

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeModule {
    @Binds
    @IntoSet
    abstract fun bindHomeGraph(homeGraph: HomeGraph): AppNavGraphBuilder
}