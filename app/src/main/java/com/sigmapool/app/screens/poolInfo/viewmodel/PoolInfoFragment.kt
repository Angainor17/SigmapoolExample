package com.sigmapool.app.screens.poolInfo.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class PoolInfoFragment: Fragment(), IPoolInfoModel {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = com.sigmapool.app.databinding.FragmentPoolInfoBinding.inflate(inflater, container, false)
        binding.vm = PoolInfoViewModel(this)
        binding.lifecycleOwner = this
        return binding.root
//        return inflater.inflate(R.layout.fragment_pool_info, container, false)

    }

    override fun getTitle() {

    }

    override fun setCurrencySelected(isSelected: Boolean) {

    }

}