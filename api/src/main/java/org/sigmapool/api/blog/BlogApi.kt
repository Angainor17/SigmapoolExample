package org.sigmapool.api.blog

import org.sigmapool.api.models.PayloadModel
import retrofit2.http.GET
import retrofit2.http.Query

internal interface BlogApi {

    @GET("api/v2/btc/blog")
    suspend fun getBlogs(
        @Query("page") page: Int,
        @Query("perPage") perPage: Int,
        @Query("lang") lang: String
    ): PayloadModel<BlogResponse>
}