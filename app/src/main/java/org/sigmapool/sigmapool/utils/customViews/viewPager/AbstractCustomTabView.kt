package org.sigmapool.sigmapool.utils.customViews.viewPager

import androidx.lifecycle.MutableLiveData
import org.sigmapool.sigmapool.screens.bottomSheetScreen.ViewPagerScreen
import org.sigmapool.sigmapool.screens.calculator.viewModel.CalcTabItemVM

abstract class AbstractCustomTabView(private val tabPositionLiveData: MutableLiveData<ViewPagerScreen>) {

    abstract val leftTab: CalcTabItemVM
    abstract val rightTab: CalcTabItemVM

    fun leftClickAction() {
        if (leftTab.value() == false) {
            leftTab.postValue(true)
            rightTab.postValue(false)
            tabPositionLiveData.postValue(ViewPagerScreen(0))
        }
    }

    fun rightClickAction() {
        if (rightTab.value() == false) {
            leftTab.postValue(false)
            rightTab.postValue(true)
            tabPositionLiveData.postValue(ViewPagerScreen(1))
        }
    }
}