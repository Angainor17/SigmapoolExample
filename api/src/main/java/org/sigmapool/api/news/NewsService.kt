package org.sigmapool.api.news

import org.sigmapool.api.models.NewsItem
import org.sigmapool.api.providers.IApiServiceProvider

internal class NewsService(apiProvider: IApiServiceProvider) : INewsService {

    private val api = apiProvider.create(NewsApi::class.java)

    override suspend fun getNews(page: Int, perPage: Int, lang: String): List<NewsItem> {
        return api.getNews(page, perPage, lang).payload?.news!!.toList()
    }
}