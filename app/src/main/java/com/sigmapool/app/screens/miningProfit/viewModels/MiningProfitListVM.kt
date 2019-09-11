package com.sigmapool.app.screens.miningProfit.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.provider.currency.ICurrencyProvider
import com.sigmapool.app.screens.home.coin.BTC
import com.sigmapool.app.screens.miningProfit.MinerBindingHelper
import com.sigmapool.app.screens.miningProfit.MinerItemMapper
import com.sigmapool.app.screens.miningProfit.MinerLoader
import com.sigmapool.app.screens.miningProfit.MiningListAdapter
import com.sigmapool.app.screens.miningProfit.params.MinerListParams
import com.sigmapool.app.utils.storages.JsonDataStorage
import com.sigmapool.common.managers.IMinerManager
import com.sigmapool.common.managers.IPoolManager
import com.sigmapool.common.models.CoinDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

class MiningProfitListVM(params: MinerListParams = MinerListParams()) : ViewModel() {

    private val minerManager by kodein.instance<IMinerManager>()
    private val coinManager by kodein.instance<IPoolManager>()
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