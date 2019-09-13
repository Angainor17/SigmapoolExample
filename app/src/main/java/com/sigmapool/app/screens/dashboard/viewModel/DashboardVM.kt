package com.sigmapool.app.screens.dashboard.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.screens.settings.viewModel.CoinToolbarVM

class DashboardVM : ViewModel() {

    val toolbarVm = CoinToolbarVM()

    val dashboardChartVM = DashboardChartVM(toolbarVm.coinProvider)
    val dashboardChartInfoVM = DashboardChartInfoVM(toolbarVm.coinProvider)
    val dashboardSubaccountsVM = DashboardSubaccountsVM()
    val dashboardEarningsVM = DashboardEarningsVM()
    val dashboardNetworkStatusVM = DashboardNetworkStatusVM()

    val isLoading = MutableLiveData(false)

    fun onRefresh() {
        isLoading.postValue(true)
    }
}