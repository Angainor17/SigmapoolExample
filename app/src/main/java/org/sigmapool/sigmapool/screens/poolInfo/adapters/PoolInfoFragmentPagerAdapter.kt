package org.sigmapool.sigmapool.screens.poolInfo.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.sigmapool.sigmapool.screens.poolInfo.fragments.PoolInfoPageFragment
import org.sigmapool.sigmapool.screens.poolInfo.params.PoolInfoItemParams
import org.sigmapool.sigmapool.screens.poolInfo.viewModel.PoolInfoCoinVM
import kotlin.math.max

class PoolInfoFragmentPagerAdapter(
    private val list: ArrayList<PoolInfoPageFragment>,
    fm: FragmentManager
) :
    FragmentPagerAdapter(fm) {

    override fun getCount(): Int = max(list.size, 1)

    override fun getItem(position: Int): Fragment {
        if (list.size == 0) {
            return PoolInfoPageFragment(PoolInfoCoinVM(PoolInfoItemParams("", "")))

        }
        return list[position]
    }

}