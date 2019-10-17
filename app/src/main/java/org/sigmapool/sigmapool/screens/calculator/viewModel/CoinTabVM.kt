package org.sigmapool.sigmapool.screens.calculator.viewModel

import androidx.lifecycle.MutableLiveData
import org.kodein.di.generic.instance
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.provider.coin.ICoinProvider
import org.sigmapool.sigmapool.screens.bottomSheetScreen.ViewPagerScreen
import org.sigmapool.sigmapool.utils.customViews.viewPager.CoinTabAdapter

class CoinTabVM(tabPositionLiveData: MutableLiveData<ViewPagerScreen>) {

    private val coinProvider by kodein.instance<ICoinProvider>()

    val adapter = CoinTabAdapter(coinProvider, tabPositionLiveData)

}