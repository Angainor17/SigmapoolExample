package org.sigmapool.sigmapool.screens.poolInfo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sigmapool.sigmapool.databinding.PoolInfoPageFragmentBinding
import org.sigmapool.sigmapool.screens.poolInfo.viewModel.PoolInfoCoinVM


class PoolInfoPageFragment(private val vm: PoolInfoCoinVM) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = PoolInfoPageFragmentBinding.inflate(inflater, container, false)
        binding.vm = vm
        binding.lifecycleOwner = this
        return binding.root
    }
}