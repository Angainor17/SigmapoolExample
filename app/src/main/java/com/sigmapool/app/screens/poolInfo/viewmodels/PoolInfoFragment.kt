package com.sigmapool.app.screens.poolInfo.viewmodels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sigmapool.app.R

class PoolInfoFragment: Fragment(), IPoolInfoModel {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pool_info, container, false)

    }

    override fun getTitle() {

    }

    override fun setCurrencySelected(isSelected: Boolean) {

    }

}