package com.sigmapool.app.screens.charges

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sigmapool.app.databinding.FragmentChargesBinding
import com.sigmapool.app.screens.charges.viewModel.ChargesVM

class ChargesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentChargesBinding.inflate(inflater, container, false)
        val vm = ChargesVM()
        binding.vm = vm

        binding.lifecycleOwner = this
        return binding.root
    }
}