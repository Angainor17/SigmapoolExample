package com.sigmapool.app.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sigmapool.app.databinding.FragmentHomeBinding
import com.sigmapool.app.screens.home.viewModel.HomeVM

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val vm = HomeVM()
        binding.vm = vm
        binding.lifecycleOwner = this

        return binding.root
    }
}