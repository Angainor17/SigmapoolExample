package com.sigmapool.app.screens.news

import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.news.vm.NewsItemVM
import com.sigmapool.common.listLibrary.datasource.SimpleMapper
import com.sigmapool.common.models.NewsDto

class NewsItemMapper(private val resProvider: IResProvider) : SimpleMapper<NewsDto, NewsItemVM>() {

    override fun map(input: NewsDto): NewsItemVM = NewsItemVM(resProvider, input)

}