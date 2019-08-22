package com.sigmapool.api.blog

import com.sigmapool.api.models.PayloadModel
import retrofit2.http.GET
import retrofit2.http.Query

internal interface BlogApi {

    @GET("api/v2/blog")
    suspend fun getBlogs(
        @Query("page") page: Int,
        @Query("perPage") perPage: Int,
        @Query("lang") lang: String
    ): PayloadModel<BlogResponse>
}