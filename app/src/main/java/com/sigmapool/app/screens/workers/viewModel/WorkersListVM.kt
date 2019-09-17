package com.sigmapool.app.screens.workers.viewModel

import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.provider.coin.ICoinProvider
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.workers.WorkerBindingHelper
import com.sigmapool.app.screens.workers.WorkerItemMapper
import com.sigmapool.app.screens.workers.WorkerLoader
import com.sigmapool.app.screens.workers.adapter.WorkerListAdapter
import com.sigmapool.app.screens.workers.params.WorkerListParams
import com.sigmapool.app.utils.vm.AuthVm
import com.sigmapool.common.listLibrary.pagedlist.SimplePagedListViewModel
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import com.sigmapool.common.managers.IWorkersManager
import org.kodein.di.generic.instance

class WorkersListVM(
    coinProvider: ICoinProvider,
    val params: WorkerListParams
) : AuthVm() {

    private val resProvider by kodein.instance<IResProvider>()
    private val workerManager by kodein.instance<IWorkersManager>(getManagerMode())

    private val bindingHelper = WorkerBindingHelper()
    val loader = WorkerLoader(params, coinProvider, workerManager)

    val itemsVM: SimplePagedListViewModel<BaseItemViewModel, Any> = SimplePagedListViewModel(
        WorkerItemMapper(resProvider),
        loader,
        bindingHelper,
        WorkerListAdapter(bindingHelper)
    ) as SimplePagedListViewModel<BaseItemViewModel, Any>
}