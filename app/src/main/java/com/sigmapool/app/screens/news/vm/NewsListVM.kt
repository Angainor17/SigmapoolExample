package com.sigmapool.app.screens.news.vm

import androidx.lifecycle.ViewModel
import com.sigmapool.app.App
import com.sigmapool.app.provider.lang.ILanguageProvider
import com.sigmapool.app.screens.news.NewsBindingHelper
import com.sigmapool.app.screens.news.NewsItemMapper
import com.sigmapool.app.screens.news.NewsLoader
import com.sigmapool.common.listLibrary.pagedlist.SimplePagedListViewModel
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import com.sigmapool.common.managers.INewsManager
import org.kodein.di.generic.instance

class NewsListVM : ViewModel() {

    private val newsManager by App.kodein.instance<INewsManager>()
    private val langProvider by App.kodein.instance<ILanguageProvider>()

    val itemsVM: SimplePagedListViewModel<BaseItemViewModel, Any>

    init {
        itemsVM = SimplePagedListViewModel(
            NewsItemMapper(),
            NewsLoader(langProvider, newsManager),
            NewsBindingHelper()
        ) as SimplePagedListViewModel<BaseItemViewModel, Any>
    }
}