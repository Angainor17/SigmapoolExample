package com.sigmapool.app.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sigmapool.app.databinding.FragmentHomeBinding
import com.sigmapool.app.navigation.showScreen
import com.sigmapool.app.screens.home.viewModel.HomeVM
import com.sigmapool.app.utils.customViews.fragment.UpdateFragment

class HomeFragment : UpdateFragment() {

    val vm = HomeVM()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        vm.fragmentLiveData.observe(this, Observer { openFragment(it) })

        binding.vm = vm
        vm.minersVM.miningProfitVM.seekBarLiveData.observe(this, Observer {})
        binding.fragmentManager = childFragmentManager
        setUpVm(vm, binding)

        return binding.root
    }

    private fun openFragment(fragmentClass: Class<out Fragment>) {
        showScreen(context!!, fragmentClass)
    }
}