package com.sigmapool.app.screens.news

import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.news.vm.NewsItemVM
import com.sigmapool.app.utils.interfaces.IBrowserVm
import com.sigmapool.common.listLibrary.datasource.SimpleMapper
import com.sigmapool.common.models.NewsDto

class NewsItemMapper(
    private val resProvider: IResProvider,
    private val browserVm: IBrowserVm
) : SimpleMapper<NewsDto, NewsItemVM>() {

    override fun map(input: NewsDto): NewsItemVM = NewsItemVM(resProvider, input, browserVm)

}