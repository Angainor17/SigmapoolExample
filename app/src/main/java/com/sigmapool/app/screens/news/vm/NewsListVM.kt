package com.sigmapool.app.screens.news.vm

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.provider.lang.ILanguageProvider
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.news.NewsBindingHelper
import com.sigmapool.app.screens.news.NewsItemMapper
import com.sigmapool.app.screens.news.NewsLoader
import com.sigmapool.app.screens.news.params.NewsListParams
import com.sigmapool.app.utils.StateVM
import com.sigmapool.app.utils.mapToViwState
import com.sigmapool.common.listLibrary.loader.ItemsLoaderState
import com.sigmapool.common.listLibrary.pagedlist.SimplePagedListViewModel
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import com.sigmapool.common.managers.INewsManager
import org.kodein.di.generic.instance

class NewsListVM(params: NewsListParams = NewsListParams()) : ViewModel(), StateVM {

    private val newsManager by kodein.instance<INewsManager>()
    private val langProvider by kodein.instance<ILanguageProvider>()
    private val resProvider by kodein.instance<IResProvider>()
    val itemsVM: SimplePagedListViewModel<BaseItemViewModel, Any> = SimplePagedListViewModel(
        NewsItemMapper(resProvider),
        NewsLoader(params.apply { lang = langProvider.getLangShortName() }, newsManager),
        NewsBindingHelper()
    ) as SimplePagedListViewModel<BaseItemViewModel, Any>

    override val viewState = Transformations.map(itemsVM.dataSourceFactory.loaderState, ItemsLoaderState::mapToViwState)

}
