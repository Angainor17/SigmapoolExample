package com.sigmapool.app.screens.news

import com.sigmapool.app.screens.news.vm.NewsItemVM
import com.sigmapool.common.listLibrary.datasource.SimpleMapper
import com.sigmapool.common.models.NewsDto

class NewsItemMapper : SimpleMapper<NewsDto, NewsItemVM>() {

    override fun map(input: NewsDto): NewsItemVM = NewsItemVM(input)

}