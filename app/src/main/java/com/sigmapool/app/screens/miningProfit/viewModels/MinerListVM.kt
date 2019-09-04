package com.sigmapool.app.screens.miningProfit.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import com.sigmapool.common.listLibrary.datasource.SimpleMapper
import com.sigmapool.common.listLibrary.loader.IItemsLoader
import com.sigmapool.common.listLibrary.loader.ItemsLoaderState
import com.sigmapool.common.listLibrary.pagedlist.SimpleAdapter
import com.sigmapool.common.models.MinerDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MinerListVM(
    val mapper: SimpleMapper<MinerDto, MinerItemVM>,
    val loader: IItemsLoader<MinerDto>,
    val adapter: SimpleAdapter<MinerItemVM>,
    val pageSize: Int = 20
) {

    var pageNumber = 0

    var isLastPage = MutableLiveData(false)
    val errorMessage = MutableLiveData<String>()
    val loaderState = MutableLiveData<ItemsLoaderState>()
    var isLoading = map(loaderState) { it == ItemsLoaderState.Loading }

    val refreshing = MutableLiveData<Boolean>()

    val message = MutableLiveData<String>()
    val items: MutableLiveData<List<MinerItemVM>> = MutableLiveData()

    init {
        onRefresh()
    }

    fun loadMoreItems() {
        loaderState.postValue(ItemsLoaderState.Loading)

        GlobalScope.launch(Dispatchers.Default) {
            val t = loader.load("", pageNumber * pageSize, pageSize)
            if (t.isSuccess) {
                items.postValue(mapper.map(t.data!!))
                loaderState.postValue(ItemsLoaderState.Idle)

                if (t.data?.size ?: 0 < pageSize) {
                    isLastPage.postValue(true)
                }
            } else {
                loaderState.postValue(ItemsLoaderState.Error)
                errorMessage.postValue(t.error)
            }
        }
        pageNumber++
    }

    fun onRefresh() {
        isLastPage.postValue(false)
        adapter.items.clear()
        adapter.notifyDataSetChanged()

        pageNumber = 0
        loadMoreItems()
    }
}

