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
        resProvider.getString(R.string.one_day),
        resProvider.getString(R.string.twenty_four_hour)
    )

    val coin = MutableLiveData<String>(coinProvider.getLabel().toUpperCase())

    val avgHashrateValue = MutableLiveData<String>("156.25")//FIXME
    val avgHashratePostfix = MutableLiveData<String>("PH/s")//FIXME

    val earnedLastValue = MutableLiveData<String>("0.04357")//FIXME

    val balanceValue = MutableLiveData<String>("0.04357")//FIXME

    val onlineWorker = MutableLiveData<String>("135")//FIXME
    val allWorker = MutableLiveData<String>("145")//FIXME

}