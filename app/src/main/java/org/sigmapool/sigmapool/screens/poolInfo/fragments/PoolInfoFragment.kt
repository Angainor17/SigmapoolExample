package org.sigmapool.sigmapool.screens.poolInfo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import org.kodein.di.generic.instance
import org.sigmapool.common.viewModels.ITitleViewModel
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.databinding.FragmentPoolInfoBinding
import org.sigmapool.sigmapool.provider.res.IResProvider
import org.sigmapool.sigmapool.screens.poolInfo.viewModel.PoolInfoVM
import org.sigmapool.sigmapool.utils.customViews.fragment.InnerFragment

class PoolInfoFragment : InnerFragment(),ITitleViewModel {

    private val resProvider by kodein.instance<IResProvider>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPoolInfoBinding.inflate(inflater, container, false)
        binding.vm = PoolInfoVM()
        binding.fragmentManager = childFragmentManager
        binding.toolbarVm = this
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun getTitle() = MutableLiveData(resProvider.getString(R.string.pool_info))
}

