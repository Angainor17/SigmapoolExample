package com.sigmapool.api.news

import com.sigmapool.common.managers.INewsManager
import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.NewsDto

internal class NewsManager(private val service: INewsService) : INewsManager {
    override suspend fun getNews(page: Int, perPage: Int, lang: String): ManagerResult<List<NewsDto>> = try {
        ManagerResult(
            service
                .getNews(page, perPage, lang)
                .map {
                    NewsDto(
                        it.title,
                        it.brief,
                        it.url,
                        it.publishedAt
                    )
                }
        )
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }
}