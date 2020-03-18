package org.sigmapool.sigmapool.screens.bottomSheetScreen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.sigmapool.sigmapool.screens.dashboard.DashboardFragment
import org.sigmapool.sigmapool.screens.earnings.EarningsFragment
import org.sigmapool.sigmapool.screens.home.HomeFragment
import org.sigmapool.sigmapool.screens.settings.SettingsFragment
import org.sigmapool.sigmapool.screens.workers.WorkersFragment


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