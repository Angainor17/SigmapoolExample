package com.sigmapool.app.screens.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sigmapool.app.databinding.FragmentCalculatorBinding
import com.sigmapool.app.screens.calculator.viewModel.CalculatorVM
import com.sigmapool.app.utils.customViews.InnerFragment

class CalculatorFragment : InnerFragment(), ICalculatorFragmentModel {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        val vm = CalculatorVM(this)
        binding.vm = vm
        binding.fragmentManager = childFragmentManager

        binding.lifecycleOwner = this
        return binding.root
    }
}