package org.sigmapool.sigmapool.screens.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sigmapool.sigmapool.databinding.FragmentCalculatorBinding
import org.sigmapool.sigmapool.screens.calculator.viewModel.CalculatorVM
import org.sigmapool.sigmapool.utils.customViews.fragment.InnerFragment

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