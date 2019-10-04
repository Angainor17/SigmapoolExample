package org.sigmapool.api.blog

import org.sigmapool.common.managers.IBlogManager
import org.sigmapool.common.models.BlogDto
import org.sigmapool.common.models.ManagerResult


internal class BlogManager(private val service: IBlogService) : IBlogManager {

    override suspend fun getBlogs(page: Int, perPage: Int, lang: String): ManagerResult<ArrayList<BlogDto>> = try {
        ManagerResult(ArrayList(service.getBlogs(page, perPage, lang).map { BlogDto(it.url, it.imageUrl) }))
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }
}