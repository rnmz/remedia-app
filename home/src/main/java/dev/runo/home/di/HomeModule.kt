package dev.runo.home.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import dev.runo.core.network.news.NewsApi
import dev.runo.core.network.title.TitleApi
import dev.runo.core_ui.navigation.AppNavGraphBuilder
import dev.runo.home.data.DefaultHomeRepository
import dev.runo.home.domain.repository.HomeRepository
import dev.runo.home.ui.navigation.HomeGraph

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeBindModule {

    @Binds
    @IntoSet
    abstract fun bindHomeGraph(homeGraph: HomeGraph): AppNavGraphBuilder

}

@Module
@InstallIn(ViewModelScoped::class)
class HomeModule {

    @Provides
    fun provideHomeRepository(titleApi: TitleApi, newsApi: NewsApi): HomeRepository {
        return DefaultHomeRepository(titleApi, newsApi)
    }

}