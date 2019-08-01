package com.sigmapool.common.listLibrary.pagedlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sigmapool.common.listLibrary.datasource.SimpleDateSourceFactory
import com.sigmapool.common.listLibrary.datasource.SimpleMapper

import com.sigmapool.common.listLibrary.loader.IItemsLoader
import com.sigmapool.common.listLibrary.loader.ItemsLoaderState
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel

class SimplePagedListViewModel<ItemViewModel : BaseItemViewModel, ItemDto>(
    mapper: SimpleMapper<ItemDto, ItemViewModel>,
    loader: IItemsLoader<ItemDto>,
    itemPerPage: Int = 20
) {

    private val dataSourceFactory = SimpleDateSourceFactory(
        loader,
        mapper
    )

    private val query = MutableLiveData<String>()

    val refreshing: LiveData<LiveData<Boolean>> = Transformations.map(dataSourceFactory.source) { dataSource ->
        Transformations.map(dataSource.loaderState) { loaderState ->
            loaderState == ItemsLoaderState.Refreshing ||
                    loaderState == ItemsLoaderState.Loading
        }
    }

    val message: LiveData<LiveData<String>> = Transformations.map(dataSourceFactory.source) { dataSource ->
        dataSource.errorMessage
    }


    private fun onQueryChanged(query: String) {
        dataSourceFactory.query = query
        items.value?.dataSource?.invalidate()
    }

    fun onRefresh() {
        items.value?.dataSource?.invalidate()
    }

    val items: LiveData<PagedList<ItemViewModel>> = LivePagedListBuilder(
        dataSourceFactory,
        PagedList.Config.Builder()
            .setPageSize(itemPerPage)
            .setEnablePlaceholders(false)
            .setPrefetchDistance(5)
            .build()
    ).build()

    init {
        query.observeForever { onQueryChanged(it) }
    }

}

