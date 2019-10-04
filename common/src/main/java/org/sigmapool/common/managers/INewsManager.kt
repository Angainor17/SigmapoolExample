package org.sigmapool.common.managers

import org.sigmapool.common.models.ManagerResult
import org.sigmapool.common.models.NewsDto

interface INewsManager {
    suspend fun getNews(page: Int, perPage: Int, lang: String): ManagerResult<List<NewsDto>>
}