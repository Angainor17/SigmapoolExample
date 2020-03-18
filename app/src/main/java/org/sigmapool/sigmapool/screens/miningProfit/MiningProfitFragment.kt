package org.sigmapool.sigmapool.screens.miningProfit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import org.sigmapool.sigmapool.databinding.FragmentMinigProfitBinding
import org.sigmapool.sigmapool.screens.miningProfit.viewModels.MiningProfitVM
import org.sigmapool.sigmapool.utils.customViews.fragment.InnerFragment

class MiningProfitFragment : InnerFragment(), IMinerFragmentModel {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMinigProfitBinding.inflate(inflater, container, false)
        val vm = MiningProfitVM(this)
        binding.vm = vm
        vm.listVM.seekBarLiveData.observe(this, Observer {})
        binding.lifecycleOwner = this

        return binding.root
    }
}