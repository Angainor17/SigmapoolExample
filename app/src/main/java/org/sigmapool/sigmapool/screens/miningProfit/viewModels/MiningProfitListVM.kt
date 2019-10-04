package org.sigmapool.sigmapool.screens.miningProfit.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import org.sigmapool.api.kodein.AUTH_MODE
import org.sigmapool.common.managers.IMinerManager
import org.sigmapool.common.managers.IPoolManager
import org.sigmapool.common.models.CoinDto
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.provider.currency.ICurrencyProvider
import org.sigmapool.sigmapool.screens.home.coin.BTC
import org.sigmapool.sigmapool.screens.miningProfit.MinerBindingHelper
import org.sigmapool.sigmapool.screens.miningProfit.MinerItemMapper
import org.sigmapool.sigmapool.screens.miningProfit.MinerLoader
import org.sigmapool.sigmapool.screens.miningProfit.MiningListAdapter
import org.sigmapool.sigmapool.screens.miningProfit.params.MinerListParams
import org.sigmapool.sigmapool.utils.storages.JsonDataStorage

class MiningProfitListVM(params: MinerListParams = MinerListParams()) : ViewModel() {

    private val minerManager by kodein.instance<IMinerManager>()
    private val coinManager by kodein.instance<IPoolManager>(AUTH_MODE)
    private val jsonDataStorage by kodein.instance<JsonDataStorage>()
    private val currencyProvider by kodein.instance<ICurrencyProvider>()

    private val seekBarVM = SeekBarVM(MutableLiveData(currencyProvider.getCurrency().params))
    val minerAdapter = MiningListAdapter(MinerHeaderVM(seekBarVM), MinerBindingHelper())

    private var coinInfo: CoinDto? = null

    val seekBarLiveData = Transformations.map(seekBarVM.seekBarValueLiveData) { value ->
        GlobalScope.launch(Dispatchers.Default) {
            setProfitValue(value)
        }

        GlobalScope.launch(Dispatchers.Main) {
            minerAdapter.setPowerCost(seekBarVM.getSeekText(value))
            refreshMinersList()
        }
        value
    }

    val itemsVM = MinerListVM(
        MinerItemMapper(currencyProvider),
        MinerLoader(params, minerManager),
        minerAdapter
    )

    init {
        initCoinValue()
    }

    private fun setCoin(coin: CoinDto) {
        coinInfo = coin
        jsonDataStorage.put(COIN_TAG, coin)
        minerAdapter.coinInfo = coinInfo
    }

    private fun refreshMinersList() {
        minerAdapter.notifyItemRangeChanged(1, minerAdapter.itemCount)
    }

    private fun setProfitValue(value: Float) {
        minerAdapter.initPowerCost(value)
        minerAdapter.items.forEach {
            it.initPowerCost(value)
        }
    }

    private fun setCoinValue(value: Float) {
        minerAdapter.initCoin(value)
        minerAdapter.items.forEach {
            it.initCoin(value)
        }

        GlobalScope.launch(Dispatchers.Main) {
            refreshMinersList()
        }
    }

    private fun initCoinValue() {
        val json = jsonDataStorage.getJson(COIN_TAG, "")
        if (!json.isNullOrEmpty()) {
            val coinDto: CoinDto = Gson().fromJson(json, CoinDto::class.java)
            setCoin(coinDto)
            setCoinValue(coinDto.price)
            return
        }

        GlobalScope.launch(Dispatchers.IO) {
            val coin = coinManager.getCoin(BTC)
            if (coin.success) {
                setCoin(coin.data!!)
                setCoinValue(coin.data?.price ?: 0f)
                jsonDataStorage.getJson(COIN_TAG, "")
            }
        }
    }
}