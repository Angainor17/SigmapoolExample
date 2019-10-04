package org.sigmapool.sigmapool.screens.workers.viewModel

import androidx.lifecycle.MutableLiveData
import org.sigmapool.sigmapool.screens.bottomSheetScreen.ViewPagerScreen
import org.sigmapool.sigmapool.screens.settings.viewModel.CoinToolbarVM
import org.sigmapool.sigmapool.screens.workers.params.ANY_STATUS
import org.sigmapool.sigmapool.screens.workers.params.OFFLINE_STATUS
import org.sigmapool.sigmapool.screens.workers.params.ONLINE_STATUS
import org.sigmapool.sigmapool.screens.workers.params.WorkerListParams
import org.sigmapool.sigmapool.utils.vm.AuthVm
import java.util.*

class WorkersVM : AuthVm() {

    val screenPositionLiveData = MutableLiveData(ViewPagerScreen(0))

    val toolbarVm = CoinToolbarVM()
    private val coinProvider = toolbarVm.coinProvider

    val tabVm = WorkersTabVM(coinProvider, screenPositionLiveData)

    private val onlineList = WorkersListVM(coinProvider, WorkerListParams(status = ONLINE_STATUS))
    private val offlineList = WorkersListVM(coinProvider, WorkerListParams(status = OFFLINE_STATUS))
    private val allList = WorkersListVM(coinProvider, WorkerListParams(status = ANY_STATUS))

    fun getWorkerLists(): ArrayList<WorkersListVM> = arrayListOf(onlineList, offlineList, allList)

    init {
        coinProvider.addOnChangeListener { refreshAll() }

        onlineList.loader.changeListener = {
            it?.let { it1 -> tabVm.initOnlineHashrate(it1) }
        }
    }

    private fun refreshAll() {
        tabVm.initTabValues()
        getWorkerLists().forEach {
            it.itemsVM.onRefresh()
        }
    }
}