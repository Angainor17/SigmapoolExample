package com.sigmapool.app.screens.bottomSheetScreen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sigmapool.app.screens.dashboard.DashboardFragment
import com.sigmapool.app.screens.earnings.EarningsFragment
import com.sigmapool.app.screens.home.HomeFragment
import com.sigmapool.app.screens.settings.SettingsFragment
import com.sigmapool.app.screens.workers.WorkersFragment


class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return HomeFragment()
            1 -> return DashboardFragment()
            2 -> return WorkersFragment()
            3 -> return EarningsFragment()
            4 -> return SettingsFragment()
        }
        return HomeFragment()
    }

    override fun getCount(): Int = 5
}