package dev.runo.core.network.news

import dev.runo.core.network.annotation.OptionalApiKey
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    /**
     * Fetch latest news from server.
     *
     * @param page Higher page number corresponds to older news.
     * @return [Response.NewsListModel]. List of news & pagination info. Every list contains 50 items.
     */
    @GET("/news/latest")
    @OptionalApiKey
    suspend fun getLatestNews(@Query("page") page: Int = 1): Response<NewsListModel>

    /**
     * Fetch news by id.
     *
     * @param id News id.
     * @return [Response.NewsModel].
     */
    @GET("news/get")
    @OptionalApiKey
    suspend fun getNewsById(@Query("id") id: Long): Response<NewsModel>

}

