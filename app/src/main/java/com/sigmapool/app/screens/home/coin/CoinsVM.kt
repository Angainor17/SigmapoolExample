package com.sigmapool.app.screens.home.coin

import androidx.lifecycle.ViewModel
import com.sigmapool.api.kodein.BTC
import com.sigmapool.api.kodein.LTC
import com.sigmapool.app.App
import com.sigmapool.app.utils.ViewState
import com.sigmapool.common.managers.IPoolManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

class CoinsVM : ViewModel() {

    private val btcCoinVM = CoinItemVM(BTC)
    private val ltcCoinVM = CoinItemVM(LTC)

    private val btcPoolProvider by App.kodein.instance<IPoolManager>(BTC)
    private val ltcPoolProvider by App.kodein.instance<IPoolManager>(LTC)

    init {
        initItem(btcCoinVM, btcPoolProvider)
        initItem(ltcCoinVM, ltcPoolProvider)
    }

    fun getCoins() = arrayListOf(btcCoinVM, ltcCoinVM)

    private fun initItem(vm: CoinItemVM, poolManager: IPoolManager) {
        vm.viewState.postValue(ViewState.LOADING)

        GlobalScope.launch(Dispatchers.IO) {
            val coinDto = poolManager.getCoin()
            val networkDto = poolManager.getNetwork()
            val paymentDto = poolManager.getPayment()
            val profitDailyDto = poolManager.getProfitDaily()

            if (coinDto.success && networkDto.success && paymentDto.success && profitDailyDto.success) {
                vm.initVMs(coinDto.data!!, networkDto.data!!, paymentDto.data!!, profitDailyDto.data!!)
                vm.viewState.postValue(ViewState.CONTENT)
            } else {
                vm.viewState.postValue(ViewState.ERROR)
            }
        }
    }
}