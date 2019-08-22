package com.sigmapool.common.managers

import com.sigmapool.common.models.BlogDto
import com.sigmapool.common.models.ManagerResult

interface IBlogManager {

    suspend fun getBlogs(page: Int, perPage: Int, lang: String): ManagerResult<ArrayList<BlogDto>>

}