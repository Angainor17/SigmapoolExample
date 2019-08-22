package com.sigmapool.app.screens.earnings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sigmapool.app.databinding.FragmentEarningsBinding
import com.sigmapool.app.screens.earnings.viewModel.EarningsVM

class EarningsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentEarningsBinding.inflate(inflater, container, false)
        val vm = EarningsVM()
        binding.vm = vm

        binding.lifecycleOwner = this
        return binding.root
    }
}