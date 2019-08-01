package com.sigmapool.common.listLibrary.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.sigmapool.common.listLibrary.loader.IItemsLoader
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel

class SimpleDateSourceFactory<ItemDto, ItemViewModel : BaseItemViewModel>(
    private val loader: IItemsLoader<ItemDto>,
    private val mapper: SimpleMapper<ItemDto, ItemViewModel>
) : DataSource.Factory<Int, ItemViewModel>() {

    val source = MutableLiveData<SimpleDataSource<ItemDto, ItemViewModel>>()

    //TODO should be redone
    var query = ""

    override fun create(): DataSource<Int, ItemViewModel> {
        val simpleDataSource = SimpleDataSource(query, loader, mapper)
        source.postValue(simpleDataSource)
        return simpleDataSource
    }
}