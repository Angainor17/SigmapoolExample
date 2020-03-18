package org.sigmapool.sigmapool.screens.news.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import org.kodein.di.generic.instance
import org.sigmapool.common.listLibrary.HeaderListVM
import org.sigmapool.common.listLibrary.loader.ItemsLoaderState
import org.sigmapool.common.managers.INewsManager
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.provider.lang.ILocaleProvider
import org.sigmapool.sigmapool.provider.res.IResProvider
import org.sigmapool.sigmapool.screens.news.NewsBindingHelper
import org.sigmapool.sigmapool.screens.news.NewsItemMapper
import org.sigmapool.sigmapool.screens.news.NewsListAdapter
import org.sigmapool.sigmapool.screens.news.NewsLoader
import org.sigmapool.sigmapool.screens.news.params.NewsListParams
import org.sigmapool.sigmapool.utils.interfaces.IBrowserVm
import org.sigmapool.sigmapool.utils.interfaces.StateVM
import org.sigmapool.sigmapool.utils.interfaces.mapToViwState

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
