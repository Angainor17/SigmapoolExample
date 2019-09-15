package com.sigmapool.app.screens.dashboard.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.screens.settings.viewModel.CoinToolbarVM

class DashboardVM : ViewModel() {

    val toolbarVm = CoinToolbarVM()
    val coinProvider = toolbarVm.coinProvider

    val dashboardChartVM = DashboardChartVM(coinProvider)
    val dashboardChartInfoVM = DashboardChartInfoVM(coinProvider)
    val dashboardSubaccountsVM = DashboardSubAccountsVM(coinProvider)
    val dashboardEarningsVM = DashboardEarningsVM(coinProvider)
    val dashboardNetworkStatusVM = DashboardNetworkStatusVM(coinProvider)

    val isLoading = MutableLiveData(false)

    fun onRefresh() {
        isLoading.postValue(true)
    }
}