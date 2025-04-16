package dev.runo.core.network.title

import dev.runo.core.network.annotation.OptionalApiKey
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TitleApi {

    /**
     * Get popular titles from all time by rating & comments.
     *
     * @param page Higher page number corresponds to less popular titles.
     * @return [Response.TitleListModel]. List of titles & pagination info. Every list contains 50 items.
     */
    @GET("/titles/popular/")
    @OptionalApiKey
    suspend fun getPopularTitles(@Query("page") page: Int): Response<TitleListModel>

    /**
     * Get latest updated titles.
     *
     * @param page Higher page number corresponds to less popular titles.
     * @return [Response.TitleListModel]. List of titles & pagination info. Every list contains 50 items.
     */
    @GET("/titles/updated/")
    @OptionalApiKey
    suspend fun getRecentUpdatedTitles(@Query("page") page: Int): Response<TitleListModel>

    /**
     * Search title with filters.
     *
     * @param name Title name. Can be null.
     * @param genres Genres as list. Can be null.
     * @param tags Tags as list. Can be null.
     * @param years List of years to filter by. Can be null.
     * @param author Author name. Can be null.
     * @param titleType Title type. Manga, manhwa, etc. Use [TitleType] enum class.
     * @param orderType Sorting order. `true` - ascending, `false` - descending. By default - ascending.
     * @return [Response.TitleModel]. List of titles & pagination info. Every list contains 50 items.
     */
    @GET("/title/search")
    @OptionalApiKey
    suspend fun searchByFilters(
        @Query("name") name: String?,
        @Query("genres") genres: List<String>?,
        @Query("tags") tags: List<String>?,
        @Query("years") years: List<Int>?,
        @Query("author") author: String?,
        @Query("type") titleType: Int?,
        @Query("order") orderType: Boolean = true,
    )

    /**
     * Get title info by id.
     * Sometimes this API can return [dev.runo.core.network.error.NetworkErrorList.UNAUTHORIZED] or
     * [dev.runo.core.network.error.NetworkErrorList.FORBIDDEN] if the title is 18+ or has the status: Moderation.
     *
     * @param id Title id.
     * @return [Response.TitleModel]. List of titles & pagination info. Every list contains 50 items.
     */
    @GET("/titles/info")
    @OptionalApiKey
    suspend fun getTitleInfo(@Query("id") id: Long): Response<TitleModel>

    /**
     * Get chapter images by id.
     * Sometimes this API can return [dev.runo.core.network.error.NetworkErrorList.FORBIDDEN] or
     * [dev.runo.core.network.error.NetworkErrorList.UNAUTHORIZED] if the chapter is 18+ or has the status: Moderation.
     *
     * @param id Chapter id.
     * @return [Response.List.String]. List of pages URLs.
     */
    @GET("/title/chapter")
    @OptionalApiKey
    suspend fun getChapter(@Query("chapterId") id: Int): Response<List<String>>

}