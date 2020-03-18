package org.sigmapool.common.listLibrary.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import org.sigmapool.common.listLibrary.loader.IItemsLoader
import org.sigmapool.common.listLibrary.loader.ItemsLoaderState
import org.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel

class SimplePagedDateSourceFactory<ItemDto, ItemViewModel : BaseItemViewModel>(
    private val loader: IItemsLoader<ItemDto>,
    private val mapper: SimpleMapper<ItemDto, ItemViewModel>
) : DataSource.Factory<Int, ItemViewModel>() {

    val source = MutableLiveData<SimplePagedDataSource<ItemDto, ItemViewModel>>()
    val loaderState = MutableLiveData<ItemsLoaderState>()

    override fun create(): DataSource<Int, ItemViewModel> {
        source.postValue(SimplePagedDataSource("", loaderState, loader, mapper))

        return SimplePagedDataSource("", loaderState, loader, mapper)
    }
}