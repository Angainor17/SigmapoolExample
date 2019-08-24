package com.sigmapool.app.screens.poolInfo.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sigmapool.app.App
import com.sigmapool.app.R
import com.sigmapool.app.databinding.PoolInfoBtcPageFragmentBinding
import com.sigmapool.common.managers.IPoolInfoManager
import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.PoolInfoBtcDto
import org.kodein.di.generic.instance


class PoolInfoBtcPageFragment: Fragment(), IPoolInfoBtcModel{

    //TODO: refactor
    private val btcPoolInfoManager by App.kodein.instance<IPoolInfoManager>()

    override suspend fun getBtcPoolInfo(): ManagerResult<PoolInfoBtcDto> {
        return btcPoolInfoManager.getBtcPoolInfo()
    }

    var pageNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageNumber = getArguments()?.getInt(ARGUMENT_PAGE_NUMBER) ?: 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.pool_info_btc_page_fragment, null)
        val binding = PoolInfoBtcPageFragmentBinding.inflate(inflater, container, false)
        binding.vm = PoolInfoBtcViewModel(this)
        binding.lifecycleOwner = this
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




