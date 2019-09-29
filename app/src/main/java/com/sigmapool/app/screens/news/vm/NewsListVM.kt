package com.sigmapool.app.screens.news.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.provider.lang.ILocaleProvider
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.news.NewsBindingHelper
import com.sigmapool.app.screens.news.NewsItemMapper
import com.sigmapool.app.screens.news.NewsListAdapter
import com.sigmapool.app.screens.news.NewsLoader
import com.sigmapool.app.screens.news.params.NewsListParams
import com.sigmapool.app.utils.interfaces.IBrowserVm
import com.sigmapool.app.utils.interfaces.StateVM
import com.sigmapool.app.utils.interfaces.mapToViwState
import com.sigmapool.common.listLibrary.HeaderListVM
import com.sigmapool.common.listLibrary.loader.ItemsLoaderState
import com.sigmapool.common.managers.INewsManager
import org.kodein.di.generic.instance

class NewsListVM(params: NewsListParams = NewsListParams()) : ViewModel(), StateVM, IBrowserVm {

    private val newsManager by kodein.instance<INewsManager>()
    private val langProvider by kodein.instance<ILocaleProvider>()
    private val resProvider by kodein.instance<IResProvider>()

    override val urlLiveData = MutableLiveData<String>()

    private val bindingHelper = NewsBindingHelper()
    private val adapter = NewsListAdapter(bindingHelper,params)

    val itemsVM = HeaderListVM(
        NewsItemMapper(resProvider, this),
        NewsLoader(params.apply { lang = langProvider.getLocale().locale }, newsManager),
        adapter
    )

    override val viewState = map(itemsVM.loaderState, ItemsLoaderState::mapToViwState)

}
