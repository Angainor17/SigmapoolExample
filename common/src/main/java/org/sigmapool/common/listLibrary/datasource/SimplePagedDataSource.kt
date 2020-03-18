package org.sigmapool.common.listLibrary.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.sigmapool.common.listLibrary.loader.IItemsLoader
import org.sigmapool.common.listLibrary.loader.ItemsLoaderState
import org.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel


class SimplePagedDataSource<ItemDto, ItemViewModel : BaseItemViewModel>(
    private val query: String = "",
    val loaderState: MutableLiveData<ItemsLoaderState>,
    private val loader: IItemsLoader<ItemDto>,
    private val mapper: SimpleMapper<ItemDto, ItemViewModel>
) : PositionalDataSource<ItemViewModel>() {

    val errorMessage = MutableLiveData<String>()

    init {
        loaderState.postValue(ItemsLoaderState.Idle)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<ItemViewModel>) {
        loaderState.postValue(ItemsLoaderState.Loading)

        GlobalScope.launch(Dispatchers.Default) {
            val t = loader.load(query, params.startPosition, params.loadSize)
            if (t.isSuccess) {
                callback.onResult(mapper.map(t.data!!))
                loaderState.postValue(ItemsLoaderState.Idle)
            } else {
                loaderState.postValue(ItemsLoaderState.Error)
                errorMessage.postValue(t.error)
            }
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<ItemViewModel>) {
        loaderState.postValue(ItemsLoaderState.Refreshing)

        GlobalScope.launch(Dispatchers.Default) {
            val t = loader.load(query, 0, params.pageSize)
            if (t.isSuccess) {
                callback.onResult(mapper.map(t.data ?: ArrayList()), 0)
                loaderState.postValue(ItemsLoaderState.Idle)
            } else {
                loaderState.postValue(ItemsLoaderState.Error)
                errorMessage.postValue(t.error)
            }
        }
    }
}