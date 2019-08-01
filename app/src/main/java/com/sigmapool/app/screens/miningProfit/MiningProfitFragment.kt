package com.sigmapool.app.screens.miningProfit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sigmapool.app.screens.miningProfit.viewModels.MiningProfitViewModel

class MiningProfitFragment : Fragment(), IMinerFragmentModel {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = com.sigmapool.app.databinding.FragmentMinigProfitBinding.inflate(inflater, container, false)
        binding.vm = MiningProfitViewModel(this)
        binding.lifecycleOwner = this
        return binding.root
    }
}