package com.sigmapool.app.screens.calculator.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sigmapool.app.screens.calculator.CalcItemFragment
import com.sigmapool.app.screens.calculator.viewModel.CalcItemVM

class CalcTabAdapter(
    private val items: ArrayList<CalcItemVM>,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    private val fragment = items.map { CalcItemFragment(it) }

    override fun getItem(position: Int) = fragment[position]
    override fun getCount() = items.size
}