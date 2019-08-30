package com.sigmapool.app.screens.poolInfo.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sigmapool.app.App
import com.sigmapool.app.R
import com.sigmapool.app.databinding.PoolInfoBtcPageFragmentBinding
import com.sigmapool.common.managers.IPoolInfoManager
import com.sigmapool.common.models.*
import org.kodein.di.generic.instance


class PoolInfoBtcPageFragment: Fragment(), IPoolInfoBtcModel{

    override suspend fun getSettlementDetails(coin: String): ManagerResult<SettlementDetailsDto> {
        return btcPoolInfoManager.getSettlementDetails(coin)
    }

    override suspend fun getPayment(coin: String): ManagerResult<PaymentDto> {
        return btcPoolInfoManager.getPayment(coin)
    }

    //TODO: refactor
    private val btcPoolInfoManager by App.kodein.instance<IPoolInfoManager>()

    override suspend fun getDailyProfit(coin:String): ManagerResult<DailyProfitDto> {
        return btcPoolInfoManager.getDailyProfit(coin)
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val urlsTextView: TextView = view.findViewById(R.id.btcUrlsTextView)
        // urlsTextView.movementMethod = LinkMovementMethod.getInstance() //TODO: unblock, if you want to get away
        // TODO: with binding adapter bind that field with xml for cleaning findViewById() call
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




