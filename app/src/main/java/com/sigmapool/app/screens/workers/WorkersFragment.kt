package com.sigmapool.app.screens.workers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sigmapool.app.databinding.FragmentWorkersBinding
import com.sigmapool.app.screens.workers.viewModel.WorkersVM


class WorkersFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentWorkersBinding.inflate(inflater, container, false)
        val vm = WorkersVM()
        binding.vm = vm

        binding.lifecycleOwner = this
        return binding.root
    }
}