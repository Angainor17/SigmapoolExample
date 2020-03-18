package org.sigmapool.sigmapool.screens.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sigmapool.sigmapool.databinding.FragmentCalculatorItemBinding
import org.sigmapool.sigmapool.screens.calculator.viewModel.CalcItemVM

class CalcItemFragment(val vm: CalcItemVM) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentCalculatorItemBinding.inflate(inflater, container, false)
        binding.vm = vm
        binding.lifecycleOwner = this

        return binding.root
    }
}