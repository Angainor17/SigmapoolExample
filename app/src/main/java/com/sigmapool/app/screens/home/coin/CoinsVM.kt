package com.sigmapool.app.screens.home.coin

import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.utils.ViewState
import com.sigmapool.common.managers.IPoolManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance


class CoinsVM : ViewModel() {

    private val btcCoinVM = CoinItemVM(BTC, R.mipmap.ic_btc)
    private val ltcCoinVM = CoinItemVM(LTC, R.mipmap.ic_ltc)

    private val poolManager by kodein.instance<IPoolManager>()

    init {
        initItem(btcCoinVM)
    }

    fun getCoins() = arrayListOf(btcCoinVM, ltcCoinVM)

    private fun initItem(vm: CoinItemVM) {
        vm.viewState.postValue(ViewState.LOADING)
        val coin = vm.coinLabel

        GlobalScope.launch(Dispatchers.IO) {
            val coinDto = poolManager.getCoin(coin)
            val networkDto = poolManager.getNetwork(coin)
            val paymentDto = poolManager.getPayment(coin)
            val profitDailyDto = poolManager.getProfitDaily(coin)

            if (coinDto.success && networkDto.success && paymentDto.success && profitDailyDto.success) {
                vm.initVMs(coinDto.data!!, networkDto.data!!, paymentDto.data!!, profitDailyDto.data!!)
                vm.viewState.postValue(ViewState.CONTENT)
            } else {
                vm.viewState.postValue(ViewState.ERROR)
            }
        }
    }
}