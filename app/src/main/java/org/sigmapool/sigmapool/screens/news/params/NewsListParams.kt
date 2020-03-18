package org.sigmapool.sigmapool.screens.news.params

const val NEWS_PAGE_SIZE = 20

class NewsListParams(
    val pageSize: Int = NEWS_PAGE_SIZE,
    val displayedSize: Int = pageSize,
    var lang: String = ""
)