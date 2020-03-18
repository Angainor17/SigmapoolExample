package org.sigmapool.api.news

import org.sigmapool.common.managers.INewsManager
import org.sigmapool.common.models.ManagerResult
import org.sigmapool.common.models.NewsDto

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