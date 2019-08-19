package com.sigmapool.app.screens.miningProfit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sigmapool.app.databinding.FragmentMinigProfitBinding
import com.sigmapool.app.screens.miningProfit.viewModels.MiningProfitViewModel

class MiningProfitFragment : Fragment(), IMinerFragmentModel {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMinigProfitBinding.inflate(inflater, container, false)
        val vm = MiningProfitViewModel(this)
        binding.vm = vm
        vm.seekBarLiveData.observe(this, Observer {})
        binding.lifecycleOwner = this
        return binding.root
    }
}