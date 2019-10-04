package org.sigmapool.sigmapool.screens.news

import org.sigmapool.common.listLibrary.loader.IItemsLoader
import org.sigmapool.common.listLibrary.loader.LoaderResult
import org.sigmapool.common.managers.INewsManager
import org.sigmapool.common.models.NewsDto
import org.sigmapool.sigmapool.screens.news.params.NewsListParams

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