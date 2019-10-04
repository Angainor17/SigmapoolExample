package org.sigmapool.sigmapool.screens.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sigmapool.sigmapool.databinding.FragmentNewsBinding
import org.sigmapool.sigmapool.screens.news.vm.NewsVM
import org.sigmapool.sigmapool.utils.customViews.fragment.InnerFragment

class NewsFragment : InnerFragment(), INewsFragmentModel {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentNewsBinding.inflate(inflater, container, false)
        val vm = NewsVM(this)
        binding.vm = vm

        setUpVm(vm, binding)

        return binding.root
    }
}