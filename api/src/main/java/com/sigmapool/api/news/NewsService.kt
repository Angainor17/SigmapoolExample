package com.sigmapool.api.news

import com.sigmapool.api.models.NewsItem
import retrofit2.Retrofit

internal class NewsService(retrofit: Retrofit) : INewsService {

    private val api = retrofit.create(NewsApi::class.java)

    override suspend fun getNews(page: Int, perPage: Int, lang: String): List<NewsItem> {
        return api.getNews(page, perPage, lang).payload?.news!!.toList()
    }
}