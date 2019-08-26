package com.sigmapool.app.screens.news

import com.sigmapool.app.provider.lang.ILanguageProvider
import com.sigmapool.common.listLibrary.loader.IItemsLoader
import com.sigmapool.common.listLibrary.loader.LoaderResult
import com.sigmapool.common.managers.INewsManager
import com.sigmapool.common.models.NewsDto

const val NEWS_PAGE_SIZE = 20

class NewsLoader(
    private val langProvider: ILanguageProvider,
    private val manager: INewsManager
) : IItemsLoader<NewsDto> {

    override suspend fun load(query: String, offset: Int, limit: Int): LoaderResult<List<NewsDto>> {
        if (offset % limit != 0) return LoaderResult(ArrayList())

        val result = manager.getNews((offset / NEWS_PAGE_SIZE) + 1, NEWS_PAGE_SIZE, langProvider.getLangShortName())

        return if (result.success) {
            LoaderResult(result.data)
        } else {
            LoaderResult(error = result.error)
        }
    }
}