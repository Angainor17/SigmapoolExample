package org.sigmapool.sigmapool.screens.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sigmapool.sigmapool.databinding.FragmentDashboardBinding
import org.sigmapool.sigmapool.screens.dashboard.viewModel.DashboardVM

class DashboardFragment : Fragment() {

    val vm = DashboardVM()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDashboardBinding.inflate(inflater, container, false)
        binding.vm = vm
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (menuVisible) {
            vm.dashboardChartVM.onStart()
        }
    }
}