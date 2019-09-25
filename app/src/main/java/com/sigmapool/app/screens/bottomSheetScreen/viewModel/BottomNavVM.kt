package com.sigmapool.app.screens.bottomSheetScreen.viewModel

import android.view.MenuItem
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sigmapool.app.R
import com.sigmapool.app.screens.bottomSheetScreen.IBottomSheetScreen
import com.sigmapool.app.screens.bottomSheetScreen.ViewPagerScreen

class BottomNavVM(val bottomSheetScreen: IBottomSheetScreen) : ViewModel(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    val screenPositionLiveData = MutableLiveData(ViewPagerScreen(0))

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        navigateTo(getScreenPosition(item.itemId))
        return true
    }

    private fun getScreenPosition(screenId: Int) = when (screenId) {
        R.id.home -> 0
        R.id.dashboard -> 1
        R.id.workers -> 2
        R.id.earnings -> 3
        R.id.settings -> 4
        else -> 0
    }

    private fun navigateTo(position: Int) {
        screenPositionLiveData.postValue(ViewPagerScreen(position))
    }
}