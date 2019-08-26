package com.sigmapool.api.news

import com.sigmapool.api.models.NewsItem


internal interface INewsService {
    suspend fun getNews(page: Int, perPage: Int, lang: String): List<NewsItem>
}

