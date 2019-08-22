package com.sigmapool.api.blog

import com.sigmapool.api.models.Blog

internal interface IBlogService {

    suspend fun getBlogs(page: Int, perPage: Int, lang: String): ArrayList<Blog>
}

