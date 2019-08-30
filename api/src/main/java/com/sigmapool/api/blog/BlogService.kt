package com.sigmapool.api.blog

import com.sigmapool.api.models.Blog
import com.sigmapool.api.providers.IApiServiceProvider

internal class BlogService(apiProvider: IApiServiceProvider) : IBlogService {

    private val api: BlogApi = apiProvider.create(BlogApi::class.java)

    override suspend fun getBlogs(page: Int, perPage: Int, lang: String): ArrayList<Blog> {
        return api.getBlogs(page, perPage, lang).payload!!.blog
    }
}