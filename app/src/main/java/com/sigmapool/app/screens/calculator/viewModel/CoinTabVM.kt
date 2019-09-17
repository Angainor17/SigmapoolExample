package com.sigmapool.app.screens.calculator.viewModel

import androidx.lifecycle.MutableLiveData
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.utils.customViews.viewPager.AbstractCustomTabView
import org.kodein.di.generic.instance

class CoinTabVM(tabPositionLiveData: MutableLiveData<Int>) : AbstractCustomTabView(tabPositionLiveData) {

    private val resProvider by kodein.instance<IResProvider>()

    override val leftTab = CalcTabItemVM(
        resProvider.getString(R.string.bitcoin),
        R.mipmap.ic_btc,
        MutableLiveData(true)
    )

    override val rightTab = CalcTabItemVM(
        resProvider.getString(R.string.litecoin),
        R.mipmap.ic_ltc,
        MutableLiveData(false)
    )
}