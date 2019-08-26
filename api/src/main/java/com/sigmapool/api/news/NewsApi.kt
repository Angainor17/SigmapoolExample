package com.sigmapool.api.news

import com.sigmapool.api.models.PayloadModel
import retrofit2.http.GET
import retrofit2.http.Query

internal interface NewsApi {

    @GET("api/v2/btc/news")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("perPage") perPage: Int,
        @Query("lang") lang: String
    ): PayloadModel<NewsListResponse>
}