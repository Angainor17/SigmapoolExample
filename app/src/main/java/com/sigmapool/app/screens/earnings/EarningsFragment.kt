package com.sigmapool.app.screens.earnings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sigmapool.app.databinding.FragmentEarningsBinding
import com.sigmapool.app.screens.earnings.viewModel.EarningsVM
import com.sigmapool.app.utils.customViews.fragment.UpdateFragment

class EarningsFragment : UpdateFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEarningsBinding.inflate(inflater, container, false)
        val vm = EarningsVM()
        binding.vm = vm
        setUpVm(vm, binding)

        return binding.root
    }
}