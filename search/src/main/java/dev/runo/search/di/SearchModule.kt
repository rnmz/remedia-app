package dev.runo.search.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import dev.runo.core.navigation.AppNavGraphBuilder
import dev.runo.search.ui.navigation.SearchGraph

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchModule {
    @Binds
    @IntoSet
    abstract fun bindSearchModule(searchGraph: SearchGraph): AppNavGraphBuilder
}