package com.sigmapool.app.screens.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sigmapool.app.databinding.FragmentSettingsBinding
import com.sigmapool.app.screens.settings.viewModel.SettingsVM


class SettingsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val vm = SettingsVM()
        binding.vm = vm

        binding.lifecycleOwner = this
        return binding.root
    }
}