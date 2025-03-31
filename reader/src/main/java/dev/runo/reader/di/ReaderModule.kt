package dev.runo.reader.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import dev.runo.core_ui.navigation.AppNavGraphBuilder
import dev.runo.reader.ui.navigation.ReaderGraph

@Module
@InstallIn(SingletonComponent::class)
abstract class ReaderModule {
    @Binds
    @IntoSet
    abstract fun bindReaderGraph(readerGraph: ReaderGraph): AppNavGraphBuilder
}