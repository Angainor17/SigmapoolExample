package com.sigmapool.app.screens.news

import com.sigmapool.app.screens.news.params.NewsListParams
import com.sigmapool.common.listLibrary.loader.IItemsLoader
import com.sigmapool.common.listLibrary.loader.LoaderResult
import com.sigmapool.common.managers.INewsManager
import com.sigmapool.common.models.NewsDto

class NewsLoader(
    private val params: NewsListParams,
    private val manager: INewsManager
) : IItemsLoader<NewsDto> {

    override suspend fun load(query: String, offset: Int, limit: Int): LoaderResult<List<NewsDto>> {
        if (offset % limit != 0) return LoaderResult(ArrayList())

        val result = manager.getNews((offset / params.pageSize) + 1, params.pageSize, params.lang)

        return if (result.success) {
            LoaderResult(result.data)
        } else {
            LoaderResult(error = result.error)
        }
    }
}