package org.sigmapool.sigmapool.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.sigmapool.sigmapool.databinding.FragmentHomeBinding
import org.sigmapool.sigmapool.navigation.showScreen
import org.sigmapool.sigmapool.screens.home.viewModel.HomeVM
import org.sigmapool.sigmapool.utils.customViews.fragment.UpdateFragment

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
        showScreen(activity!! as AppCompatActivity, fragmentClass)
    }
}