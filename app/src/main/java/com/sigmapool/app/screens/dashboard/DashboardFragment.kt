package com.sigmapool.app.screens.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sigmapool.app.databinding.FragmentDashboardBinding
import com.sigmapool.app.screens.dashboard.viewModel.DashboardVM

class DashboardFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val vm = DashboardVM()
        binding.vm = vm

        binding.lifecycleOwner = this
        return binding.root
    }
}