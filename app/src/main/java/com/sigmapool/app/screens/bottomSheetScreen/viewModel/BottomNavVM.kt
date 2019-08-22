package com.sigmapool.app.screens.bottomSheetScreen.viewModel

import android.view.MenuItem
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sigmapool.app.R
import com.sigmapool.app.screens.bottomSheetScreen.IBottomSheetScreen

class BottomNavVM(val bottomSheetScreen: IBottomSheetScreen) : ViewModel(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {

                return true
            }
            R.id.dashboard -> {

                return true
            }
            R.id.workers -> {

                return true
            }
            R.id.charges -> {

                return true
            }
            R.id.settings -> {

                return true
            }
        }
        return false
    }
}