package com.sigmapool.app.screens.workers.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sigmapool.app.screens.workers.WorkersListFragment
import com.sigmapool.app.screens.workers.viewModel.WorkersListVM

class WorkerViewPagerAdapter(
    private val items: ArrayList<WorkersListVM>,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    private val fragment = items.map {
        val fragment = WorkersListFragment()
        fragment.vm = it
        fragment
    }

    override fun getItem(position: Int): Fragment = fragment[position]

    override fun getCount(): Int = items.size

}