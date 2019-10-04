package org.sigmapool.sigmapool.screens.poolInfo.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.kodein.di.generic.instance
import org.sigmapool.common.managers.IPoolInfoManager
import org.sigmapool.sigmapool.App
import org.sigmapool.sigmapool.databinding.PoolInfoLtcPageFragmentBinding
import org.sigmapool.sigmapool.provider.lang.ILocaleProvider
import org.sigmapool.sigmapool.screens.poolInfo.model.IPoolInfoLtcModel


class PoolInfoLtcPageFragment(private val vm: PoolInfoLtcViewModel) : Fragment(),
    IPoolInfoLtcModel {

    private val localeProvider by App.kodein.instance<ILocaleProvider>()
    private val btcPoolInfoManager by App.kodein.instance<IPoolInfoManager>()

    init {
        vm.model = this
    }

    override suspend fun getSettlementDetails(coin: String) =
        btcPoolInfoManager.getSettlementDetails(coin, localeProvider.getLocale().locale)

    override suspend fun getPayment(coin: String) = btcPoolInfoManager.getPayment(coin)
    override suspend fun getDailyProfit(coin: String) = btcPoolInfoManager.getDailyProfit(coin)
    override suspend fun getLtcPoolInfo() = btcPoolInfoManager.getLtcPoolInfo()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = PoolInfoLtcPageFragmentBinding.inflate(inflater, container, false)

        binding.vm = vm
        binding.lifecycleOwner = this
        return binding.root
    }
}




