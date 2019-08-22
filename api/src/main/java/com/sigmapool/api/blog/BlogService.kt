package com.sigmapool.api.blog

import com.sigmapool.api.models.Blog
import retrofit2.Retrofit

internal class BlogService(retrofit: Retrofit) : IBlogService {

    private val api: BlogApi = retrofit.create(BlogApi::class.java)

    override suspend fun getBlogs(page: Int, perPage: Int, lang: String): ArrayList<Blog> {
        return api.getBlogs(page, perPage, lang).payload!!.blog
    }
}