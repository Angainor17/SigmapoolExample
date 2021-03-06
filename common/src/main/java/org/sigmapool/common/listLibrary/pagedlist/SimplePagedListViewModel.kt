package org.sigmapool.common.listLibrary.pagedlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import org.sigmapool.common.listLibrary.IItemBindingHelper
import org.sigmapool.common.listLibrary.datasource.SimpleMapper
import org.sigmapool.common.listLibrary.datasource.SimplePagedDateSourceFactory
import org.sigmapool.common.listLibrary.loader.IItemsLoader
import org.sigmapool.common.listLibrary.loader.ItemsLoaderState
import org.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel

class SimplePagedListViewModel<ItemViewModel : BaseItemViewModel, ItemDto>(
    mapper: SimpleMapper<ItemDto, ItemViewModel>,
    loader: IItemsLoader<ItemDto>,
    bindingHelper: IItemBindingHelper,
    val pagedRecyclerAdapter: SimplePagedAdapter = SimplePagedAdapter(bindingHelper),
    itemPerPage: Int = 20
) {

    val dataSourceFactory = SimplePagedDateSourceFactory(loader, mapper)

    val refreshing: LiveData<LiveData<Boolean>> = Transformations.map(dataSourceFactory.source) { dataSource ->
        Transformations.map(dataSource.loaderState) { loaderState ->
            loaderState == ItemsLoaderState.Refreshing ||
                    loaderState == ItemsLoaderState.Loading
        }
    }

    val message: LiveData<LiveData<String>> = Transformations.map(dataSourceFactory.source) { dataSource ->
        dataSource.errorMessage
    }

    fun onRefresh() {
        items.value?.dataSource?.invalidate()
    }

    val items: LiveData<PagedList<ItemViewModel>> = LivePagedListBuilder(
        dataSourceFactory,
        PagedList.Config.Builder()
            .setPageSize(itemPerPage)
            .setEnablePlaceholders(false)
            .build()
    ).build()
}

