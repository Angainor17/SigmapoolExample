package org.sigmapool.common.managers

import org.sigmapool.common.models.BlogDto
import org.sigmapool.common.models.ManagerResult

interface IBlogManager {

    suspend fun getBlogs(page: Int, perPage: Int, lang: String): ManagerResult<ArrayList<BlogDto>>

}