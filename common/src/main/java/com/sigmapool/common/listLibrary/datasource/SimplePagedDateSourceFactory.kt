package com.sigmapool.common.listLibrary.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.sigmapool.common.listLibrary.loader.IItemsLoader
import com.sigmapool.common.listLibrary.loader.ItemsLoaderState
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel

class SimplePagedDateSourceFactory<ItemDto, ItemViewModel : BaseItemViewModel>(
    loader: IItemsLoader<ItemDto>,
    mapper: SimpleMapper<ItemDto, ItemViewModel>
) : DataSource.Factory<Int, ItemViewModel>() {

    val source = MutableLiveData<SimplePagedDataSource<ItemDto, ItemViewModel>>()
    val loaderState = MutableLiveData<ItemsLoaderState>()
    val simpleDataSource = SimplePagedDataSource("", loaderState, loader, mapper)

    override fun create(): DataSource<Int, ItemViewModel> {
        source.postValue(simpleDataSource)
        return simpleDataSource
    }
}