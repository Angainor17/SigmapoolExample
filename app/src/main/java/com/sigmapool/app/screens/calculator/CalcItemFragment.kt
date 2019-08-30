package com.sigmapool.app.screens.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sigmapool.app.databinding.FragmentCalculatorItemBinding
import com.sigmapool.app.screens.calculator.viewModel.CalcItemVM

class CalcItemFragment(val vm: CalcItemVM) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentCalculatorItemBinding.inflate(inflater, container, false)
        binding.vm = vm
        binding.lifecycleOwner = this

        return binding.root
    }
}