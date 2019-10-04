package org.sigmapool.api.blog

import org.sigmapool.api.models.Blog
import org.sigmapool.api.providers.IApiServiceProvider

internal class BlogService(apiProvider: IApiServiceProvider) : IBlogService {

    private val api: BlogApi = apiProvider.create(BlogApi::class.java)

    override suspend fun getBlogs(page: Int, perPage: Int, lang: String): ArrayList<Blog> {
        return api.getBlogs(page, perPage, lang).payload!!.blog
    }
}