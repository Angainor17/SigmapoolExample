package org.sigmapool.sigmapool.screens.news

import org.sigmapool.common.listLibrary.datasource.SimpleMapper
import org.sigmapool.common.models.NewsDto
import org.sigmapool.sigmapool.provider.res.IResProvider
import org.sigmapool.sigmapool.screens.news.vm.NewsItemVM
import org.sigmapool.sigmapool.utils.interfaces.IBrowserVm

class NewsItemMapper(
    private val resProvider: IResProvider,
    private val browserVm: IBrowserVm
) : SimpleMapper<NewsDto, NewsItemVM>() {

    override fun map(input: NewsDto): NewsItemVM = NewsItemVM(resProvider, input, browserVm)

}