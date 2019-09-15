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

    val listItems = MutableLiveData<ArrayList<SubAccountItemVM>>()


    init {
        listItems.postValue(//FIXME
            ArrayList(List(3) {
                SubAccountItemVM(
                    "Name $it",
                    "$it H/s",
                    "$it,0",
                    coinProvider.getLabel()
                )
            })
        )
    }

    fun onHeaderClick() {
        isDropdownOpen.postValue(!(isDropdownOpen.value ?: false))
    }
}