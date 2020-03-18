package org.sigmapool.api.news

import org.sigmapool.api.models.NewsItem


internal interface INewsService {
    suspend fun getNews(page: Int, perPage: Int, lang: String): List<NewsItem>
}

