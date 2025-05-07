package dev.runo.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.runo.core.BuildConfig
import dev.runo.core.DataStoreManager
import dev.runo.core.common.RELEASE_API_DOMAIN
import dev.runo.core.network.AuthInterceptor
import dev.runo.core.network.file.FileApi
import dev.runo.core.network.news.NewsApi
import dev.runo.core.network.title.TitleApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    @Provides
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }

    @Provides
    fun provideAuthInterceptor(dataStoreManager: DataStoreManager): AuthInterceptor {
        return AuthInterceptor(dataStoreManager)
    }

    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    fun provideHttpClient(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RELEASE_API_DOMAIN)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create<NewsApi>(NewsApi::class.java)
    }

    @Provides
    fun provideTitleApi(retrofit: Retrofit): TitleApi {
        return retrofit.create<TitleApi>(TitleApi::class.java)
    }

    @Provides
    fun provideCdnApi(retrofit: Retrofit): FileApi {
        return retrofit.create<FileApi>(FileApi::class.java)
    }

}