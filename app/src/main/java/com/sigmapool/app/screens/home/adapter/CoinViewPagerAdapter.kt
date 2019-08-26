package com.sigmapool.app.screens.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sigmapool.app.screens.home.coin.CoinFragment
import com.sigmapool.app.screens.home.coin.CoinItemVM

class CoinViewPagerAdapter(
    private val items: ArrayList<CoinItemVM>,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    private val fragment = items.map { CoinFragment(it) }

    override fun getItem(position: Int): Fragment = fragment[position]

    override fun getCount(): Int = items.size

}