package org.sigmapool.sigmapool.screens.workers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sigmapool.sigmapool.databinding.FragmentWorkersListBinding
import org.sigmapool.sigmapool.screens.workers.viewModel.WorkersListVM

class WorkersListFragment : Fragment() {

    var vm: WorkersListVM? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentWorkersListBinding.inflate(inflater, container, false)
        binding.vm = vm
        binding.lifecycleOwner = this

        return binding.root
    }
}