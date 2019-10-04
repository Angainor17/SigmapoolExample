package org.sigmapool.sigmapool.screens.earnings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sigmapool.sigmapool.databinding.FragmentEarningsBinding
import org.sigmapool.sigmapool.screens.earnings.viewModel.EarningsVM
import org.sigmapool.sigmapool.utils.customViews.fragment.UpdateFragment

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