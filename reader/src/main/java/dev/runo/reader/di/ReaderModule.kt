package dev.runo.reader.di

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
import dev.runo.reader.data.DefaultReaderRepository
import dev.runo.reader.domain.repository.ReaderRepository
import dev.runo.reader.ui.navigation.ReaderGraph

@Module
@InstallIn(SingletonComponent::class)
abstract class ReaderBindModule {

    @Binds
    @IntoSet
    abstract fun bindReaderGraph(readerGraph: ReaderGraph): AppNavGraphBuilder

}

@Module
@InstallIn(ViewModelComponent::class)
class ReaderModule {

    @Provides
    fun provideReaderRepository(titleApi: TitleApi, fileApi: FileApi): ReaderRepository {
        return DefaultReaderRepository(titleApi, fileApi)
    }

}