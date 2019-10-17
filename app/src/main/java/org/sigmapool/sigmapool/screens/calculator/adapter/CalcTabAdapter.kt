package org.sigmapool.sigmapool.screens.calculator.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.sigmapool.sigmapool.screens.calculator.CalcItemFragment
import org.sigmapool.sigmapool.screens.calculator.viewModel.CalcItemVM

class CalcTabAdapter(
    private val items: List<CalcItemVM>,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    private val fragment = items.map { CalcItemFragment(it) }

    override fun getItem(position: Int) = fragment[position]
    override fun getCount() = items.size
}