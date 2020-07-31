package org.sigmapool.sigmapool.screens.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.sigmapool.sigmapool.screens.home.coin.CoinFragment
import org.sigmapool.sigmapool.screens.home.coin.CoinItemVM

class CoinViewPagerAdapter(
    items: ArrayList<CoinItemVM>,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    private val fragment = items.map {
        val fragment = CoinFragment()
        fragment.vm = it
        fragment
    }

    override fun getItem(position: Int): Fragment = fragment[position]

    override fun getCount(): Int = fragment.size

}