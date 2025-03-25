package dev.runo.core.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.Multibinds
import dev.runo.core.navigation.AppNavGraphBuilder


@Module
@InstallIn(SingletonComponent::class)
abstract class CoreBindModule {
    @Multibinds
    abstract fun bindNavGraphRoutes(): Set<AppNavGraphBuilder>
}