package com.sigmapool.app.screens.poolInfo.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sigmapool.app.R
import com.sigmapool.app.databinding.PoolInfoBtcPageFragmentBinding


class PoolInfoBtcPageFragment: Fragment(), IPoolInfoBtcModel {

    var pageNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments()?.getInt(ARGUMENT_PAGE_NUMBER) ?: 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.pool_info_btc_page_fragment, null)
        val binding = PoolInfoBtcPageFragmentBinding.inflate(inflater, container, false)
        binding.vm = PoolInfoBtcViewModel(this)
        return binding.root
    }

    companion object {
        val ARGUMENT_PAGE_NUMBER: String = "arg_page_number"
        fun newInstance(page: Int): Fragment{
            val pageFragment = PoolInfoBtcPageFragment()
            val arguments = Bundle()
            arguments.putInt(ARGUMENT_PAGE_NUMBER, page)
            pageFragment.arguments = arguments
            return pageFragment
        }
    }
}




