package com.sigmapool.app.screens.dashboard.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.coin.ICoinProvider
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.utils.customViews.CustomSwitchVm
import org.kodein.di.generic.instance

class DashboardChartInfoVM(
    private val coinProvider: ICoinProvider
) : ViewModel() {

    private val resProvider by kodein.instance<IResProvider>()

    val customSwitchVm = CustomSwitchVm(
        resProvider.getString(R.string.one_hour),
        resProvider.getString(R.string.one_day)
    )

    val coin = MutableLiveData<String>(coinProvider.getLabel().toUpperCase())

    val avgHashrateValue = MutableLiveData<String>("156.25")
    val avgHashratePostfix = MutableLiveData<String>("PH/s")

    val earnedLastValue = MutableLiveData<String>("0.04357")

    val balanceValue = MutableLiveData<String>("0.04357")

    val onlineWorker = MutableLiveData<String>("135")
    val allWorker = MutableLiveData<String>("145")

}