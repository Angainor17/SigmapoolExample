package org.sigmapool.sigmapool.screens.calculator.viewModel

import androidx.lifecycle.MutableLiveData
import org.kodein.di.generic.instance
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.res.IResProvider
import org.sigmapool.sigmapool.screens.bottomSheetScreen.ViewPagerScreen
import org.sigmapool.sigmapool.utils.customViews.viewPager.AbstractCustomTabView

class CoinTabVM(tabPositionLiveData: MutableLiveData<ViewPagerScreen>) : AbstractCustomTabView(tabPositionLiveData) {

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