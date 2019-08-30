package com.sigmapool.app.screens.poolInfo.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.viewpager.widget.PagerAdapter
import com.sigmapool.app.App
import com.sigmapool.app.R
import com.sigmapool.app.databinding.FragmentPoolInfoBinding
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.poolInfo.adapters.PoolInfoFragmentPagerAdapter
import com.sigmapool.app.screens.poolInfo.vm.PoolInfoVM
import com.sigmapool.app.utils.customViews.InnerFragment
import com.sigmapool.app.utils.customViews.SwipeFreeViewPager
import org.kodein.di.generic.instance

class PoolInfoFragment : InnerFragment(), IPoolInfoModel {

    private val resProvider by App.kodein.instance<IResProvider>()

    lateinit var pager: SwipeFreeViewPager
    lateinit var pagerAdapter: PagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentPoolInfoBinding.inflate(inflater, container, false)
        binding.vm = PoolInfoVM(this)
        binding.toolbarVm = this
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pager = (view.findViewById(R.id.pager))!!
        pagerAdapter = PoolInfoFragmentPagerAdapter(activity!!.supportFragmentManager)
        pager.adapter = pagerAdapter
    }

    override fun getTitle() = MutableLiveData(resProvider.getString(R.string.pool_info))

    override fun setCurrencySelected(isSelected: Boolean) {

    }
}

