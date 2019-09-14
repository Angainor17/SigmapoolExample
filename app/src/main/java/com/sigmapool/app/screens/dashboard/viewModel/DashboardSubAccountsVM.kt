package com.sigmapool.app.screens.dashboard.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.provider.coin.ICoinProvider

class DashboardSubAccountsVM(
    private val coinProvider: ICoinProvider
) : ViewModel() {

    val coinLiveData = MutableLiveData(coinProvider.getLabel().toUpperCase())//FIXME
    val coinValueLiveData = MutableLiveData("6,5")//FIXME
    val hashrateLiveData = MutableLiveData("13298 H/s")//FIXME

    val isDropdownOpen = MutableLiveData(false)//FIXME

}