package com.sigmapool.app.screens.workers.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.screens.settings.viewModel.CoinToolbarVM
import com.sigmapool.app.screens.workers.params.ANY_STATUS
import com.sigmapool.app.screens.workers.params.OFFLINE_STATUS
import com.sigmapool.app.screens.workers.params.ONLINE_STATUS
import com.sigmapool.app.screens.workers.params.WorkerListParams
import java.util.*

class WorkersVM : ViewModel() {

    val screenPositionLiveData = MutableLiveData(0)

    val toolbarVm = CoinToolbarVM()
    private val coinProvider = toolbarVm.coinProvider

    val tabVm = WorkersTabVM(coinProvider, screenPositionLiveData)

    private val onlineList = WorkersListVM(coinProvider, WorkerListParams(status = ONLINE_STATUS))
    private val offlineList = WorkersListVM(coinProvider, WorkerListParams(status = OFFLINE_STATUS))
    private val allList = WorkersListVM(coinProvider, WorkerListParams(status = ANY_STATUS))

    fun getWorkerLists(): ArrayList<WorkersListVM> = arrayListOf(onlineList, offlineList, allList)

    init {
        coinProvider.addOnChangeListener { refreshAll() }
    }

    private fun refreshAll() {
        tabVm.initTabValues()
        getWorkerLists().forEach {
            it.itemsVM.onRefresh()
        }
    }
}