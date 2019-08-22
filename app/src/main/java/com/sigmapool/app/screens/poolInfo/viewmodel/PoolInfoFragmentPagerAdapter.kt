package com.sigmapool.app.screens.poolInfo.viewmodel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PoolInfoFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    val PAGE_COUNT:Int = 2

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 ->  PoolInfoBtcPageFragment.newInstance(position)
            else -> PoolInfoLtcPageFragment.newInstance(position) // TODO: replace by LtcPageFragment
        }

    }

}