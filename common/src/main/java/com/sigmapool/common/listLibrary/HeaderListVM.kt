package com.sigmapool.common.listLibrary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import com.sigmapool.common.listLibrary.datasource.SimpleMapper
import com.sigmapool.common.listLibrary.loader.IItemsLoader
import com.sigmapool.common.listLibrary.loader.ItemsLoaderState
import com.sigmapool.common.listLibrary.pagedlist.SimpleAdapter
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open class HeaderListVM<DtoItem, ItemVm : BaseItemViewModel>(
    val mapper: SimpleMapper<DtoItem, ItemVm>,
    val loader: IItemsLoader<DtoItem>,
    val adapter: SimpleAdapter<ItemVm>,
    val pageSize: Int = 20
) {

    var pageNumber = 0

    var onRefreshListener: (() -> Unit)? = null

    var isLastPage = MutableLiveData(false)
    val errorMessage = MutableLiveData<String>()
    val loaderState = MutableLiveData<ItemsLoaderState>()
    val isLoading = map(loaderState) { it == ItemsLoaderState.Loading }
    val message = MutableLiveData<String>()
    val items: MutableLiveData<List<ItemVm>> = MutableLiveData()

    init {
        onRefresh()
    }

    fun loadMoreItems() {
        loaderState.postValue(ItemsLoaderState.Loading)

        GlobalScope.launch(Dispatchers.Default) {
            val t = loader.load("", pageNumber * pageSize, pageSize)
            if (t.isSuccess) {
                items.postValue(t.data?.let { mapper.map(it) })
                loaderState.postValue(ItemsLoaderState.Idle)

                if (t.data?.size ?: 0 < pageSize) {
                    isLastPage.postValue(true)
                }
                pageNumber++
            } else {
                loaderState.postValue(ItemsLoaderState.Error)
                errorMessage.postValue(t.error)
            }
        }
    }

    fun onRefresh() {
        isLastPage.postValue(false)
        adapter.items.clear()
        adapter.notifyItemRangeChanged(1, adapter.items.size)

        pageNumber = 0
        loadMoreItems()
        onRefreshListener?.invoke()
    }
}

