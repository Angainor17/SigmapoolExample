package com.sigmapool.app.screens.poolInfo.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.databinding.FragmentPoolInfoBinding
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.poolInfo.model.IPoolInfoModel
import com.sigmapool.app.utils.customViews.fragment.InnerFragment
import org.kodein.di.generic.instance

class PoolInfoFragment : InnerFragment(), IPoolInfoModel {

    private val resProvider by kodein.instance<IResProvider>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPoolInfoBinding.inflate(inflater, container, false)
        binding.vm = PoolInfoViewModel(this)
        binding.fragmentManager = childFragmentManager
        binding.toolbarVm = this
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun getTitle() = MutableLiveData(resProvider.getString(R.string.pool_info))
}

