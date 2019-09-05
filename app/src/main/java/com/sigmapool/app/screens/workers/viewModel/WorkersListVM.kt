package com.sigmapool.app.screens.workers.viewModel

import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.provider.coin.ICoinProvider
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.workers.WorkerBindingHelper
import com.sigmapool.app.screens.workers.WorkerItemMapper
import com.sigmapool.app.screens.workers.WorkerLoader
import com.sigmapool.app.screens.workers.params.WorkerListParams
import com.sigmapool.common.listLibrary.pagedlist.SimplePagedListViewModel
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import org.kodein.di.generic.instance

class WorkersListVM(
    coinProvider: ICoinProvider,
    val params: WorkerListParams
) : ViewModel() {

    private val resProvider by kodein.instance<IResProvider>()

    val itemsVM: SimplePagedListViewModel<BaseItemViewModel, Any> = SimplePagedListViewModel(
        WorkerItemMapper(resProvider),
        WorkerLoader(params, coinProvider),
        WorkerBindingHelper()
    ) as SimplePagedListViewModel<BaseItemViewModel, Any>
}