package com.sigmapool.app.screens.poolInfo.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import com.sigmapool.app.R
import com.sigmapool.app.views.SwipeFreeViewPager

class PoolInfoFragment: Fragment(), IPoolInfoModel {

    lateinit var pager: SwipeFreeViewPager
    lateinit var pagerAdapter: PagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = com.sigmapool.app.databinding.FragmentPoolInfoBinding.inflate(inflater, container, false)
        binding.vm = PoolInfoViewModel(this)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pager = (view.findViewById(R.id.pager))!!
        pagerAdapter = PoolInfoFragmentPagerAdapter(activity!!.supportFragmentManager)
        pager.adapter = pagerAdapter

    }

    override fun getTitle() {

    }

    override fun setCurrencySelected(isSelected: Boolean) {

    }

}

