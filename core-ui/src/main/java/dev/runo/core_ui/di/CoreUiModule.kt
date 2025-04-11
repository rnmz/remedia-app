package dev.runo.core_ui.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.Multibinds
import dev.runo.core_ui.cache.ImageCache
import dev.runo.core_ui.navigation.AppNavGraphBuilder

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreUiBindModule {
    @Multibinds
    abstract fun bindNavGraphRoutes(): Set<AppNavGraphBuilder>
}

@Module
@InstallIn(SingletonComponent::class)
class CoreUiModule {
    @Provides
    fun provideImageCache(@ApplicationContext context: Context): ImageCache {
        return ImageCache(context)
    }
}