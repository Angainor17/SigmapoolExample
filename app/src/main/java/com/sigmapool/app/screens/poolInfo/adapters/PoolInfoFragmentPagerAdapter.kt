package com.sigmapool.app.screens.poolInfo.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PoolInfoFragmentPagerAdapter(
    private val list: ArrayList<Fragment>,
    fm: FragmentManager
) :
    FragmentPagerAdapter(fm) {

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Fragment = list[position]

}