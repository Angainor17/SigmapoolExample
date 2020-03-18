package org.sigmapool.sigmapool.screens.home.coin

import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import org.sigmapool.api.kodein.AUTH_MODE
import org.sigmapool.common.managers.IPoolManager
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.provider.coin.ICoinProvider
import org.sigmapool.sigmapool.utils.interfaces.ViewState

class CoinsVM : ViewModel() {

    private val coinProvider by kodein.instance<ICoinProvider>()

    private val poolManager by kodein.instance<IPoolManager>(AUTH_MODE)

    val coins = map(coinProvider.coins) {
        val items = ArrayList(it.map { CoinItemVM(it.text, it.imageUrl) })
        items.forEach { initItem(it) }
        items
    }

    private fun initItem(vm: CoinItemVM) {
        vm.viewState.postValue(ViewState.LOADING)

        GlobalScope.launch(Dispatchers.IO) {
            val coin = vm.coinLabel.toLowerCase()

            val coinDto = poolManager.getCoin(coin)
            val networkDto = poolManager.getNetwork(coin)
            val paymentDto = poolManager.getPayment(coin)
            val profitDailyDto = poolManager.getProfitDaily(coin)

            if (coinDto.success && networkDto.success && paymentDto.success && profitDailyDto.success) {
                vm.initVMs(
                    coinDto.data!!,
                    networkDto.data!!,
                    paymentDto.data!!,
                    profitDailyDto.data!!
                )
                vm.viewState.postValue(ViewState.CONTENT)
            } else {
                vm.viewState.postValue(ViewState.ERROR)
            }
        }
    }
}