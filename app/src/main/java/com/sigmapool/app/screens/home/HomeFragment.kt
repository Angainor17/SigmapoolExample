package com.sigmapool.app.screens.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sigmapool.app.databinding.FragmentHomeBinding
import com.sigmapool.app.navigation.showScreen
import com.sigmapool.app.screens.home.viewModel.HomeVM


class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        val vm = HomeVM()
        vm.urlLiveData.observe(this, Observer { openUrlInBrowser(it) })
        vm.fragmentLiveData.observe(this, Observer { openFragment(it) })

        binding.vm = vm
        binding.fragmentManager = childFragmentManager
        binding.lifecycleOwner = this

        return binding.root
    }

    private fun openUrlInBrowser(url: String) {
        if (url.isEmpty()) return

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    private fun openFragment(fragmentClass: Class<out Fragment>) {
        showScreen(context!!, fragmentClass)
    }
}