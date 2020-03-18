package org.sigmapool.sigmapool.screens.workers.viewModel

import org.kodein.di.generic.instance
import org.sigmapool.common.listLibrary.pagedlist.SimplePagedListViewModel
import org.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import org.sigmapool.common.managers.IWorkersManager
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.provider.coin.ICoinProvider
import org.sigmapool.sigmapool.provider.res.IResProvider
import org.sigmapool.sigmapool.screens.workers.WorkerBindingHelper
import org.sigmapool.sigmapool.screens.workers.WorkerItemMapper
import org.sigmapool.sigmapool.screens.workers.WorkerLoader
import org.sigmapool.sigmapool.screens.workers.adapter.WorkerListAdapter
import org.sigmapool.sigmapool.screens.workers.params.WorkerListParams
import org.sigmapool.sigmapool.utils.vm.AuthVm

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