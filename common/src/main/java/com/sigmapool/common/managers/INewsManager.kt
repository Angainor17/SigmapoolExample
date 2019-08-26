package com.sigmapool.common.managers

import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.NewsDto

interface INewsManager {
    suspend fun getNews(page: Int, perPage: Int, lang: String): ManagerResult<List<NewsDto>>
}