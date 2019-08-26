package com.sigmapool.app.screens.news.params

private const val NEWS_PAGE_SIZE = 20

class NewsListParams(
    val pageSize: Int = NEWS_PAGE_SIZE,
    var lang: String = ""
)