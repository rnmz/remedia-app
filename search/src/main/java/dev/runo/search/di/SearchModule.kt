package dev.runo.search.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import dev.runo.core.network.FileApi
import dev.runo.core.network.title.TitleApi
import dev.runo.core_ui.navigation.AppNavGraphBuilder
import dev.runo.search.data.DefaultSearchRepository
import dev.runo.search.domain.repository.SearchRepository
import dev.runo.search.ui.navigation.SearchGraph

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchBindModule {

    @Binds
    @IntoSet
    abstract fun bindSearchModule(searchGraph: SearchGraph): AppNavGraphBuilder

}

@Module
@InstallIn(ViewModelComponent::class)
class SearchModule {

    @Provides
    fun provideSearchRepository(titleApi: TitleApi, fileApi: FileApi): SearchRepository {
        return DefaultSearchRepository(titleApi, fileApi)
    }

}