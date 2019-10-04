package org.sigmapool.api.blog

import org.sigmapool.api.models.Blog

internal interface IBlogService {

    suspend fun getBlogs(page: Int, perPage: Int, lang: String): ArrayList<Blog>
}

