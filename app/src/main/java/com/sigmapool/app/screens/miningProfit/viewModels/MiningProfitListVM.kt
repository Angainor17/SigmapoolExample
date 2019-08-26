package com.sigmapool.app.screens.miningProfit.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.models.Currency
import com.sigmapool.app.provider.currency.ICurrencyProvider
import com.sigmapool.app.screens.miningProfit.MinerBindingHelper
import com.sigmapool.app.screens.miningProfit.MinerItemMapper
import com.sigmapool.app.screens.miningProfit.MinerLoader
import com.sigmapool.app.screens.miningProfit.MiningListAdapter
import com.sigmapool.app.screens.miningProfit.params.MinerListParams
import com.sigmapool.app.utils.JsonDataStorage
import com.sigmapool.common.listLibrary.pagedlist.SimplePagedListViewModel
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import com.sigmapool.common.managers.IMinerManager
import com.sigmapool.common.managers.IPoolManager
import com.sigmapool.common.models.CoinDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

class MiningProfitListVM(params: MinerListParams = MinerListParams()) : ViewModel() {

    private val usdCurrency = Currency(1, 12, 1, R.array.array_first_and_last_1_12, 0)

    private val minerManager by kodein.instance<IMinerManager>()
    private val coinManager by kodein.instance<IPoolManager>()
    private val jsonDataStorage by kodein.instance<JsonDataStorage>()
    private val currencyProvider by kodein.instance<ICurrencyProvider>()

    private val currencyLiveData = MutableLiveData(usdCurrency)
    private val seekBarVM = SeekBarVM(currencyLiveData)
    private val minerHeaderVM = MinerHeaderVM(seekBarVM)
    private val minerBindingHelper = MinerBindingHelper()
    private val minerAdapter = MiningListAdapter(minerHeaderVM, minerBindingHelper)

    private var coinInfo: CoinDto? = null

    val itemsVM: SimplePagedListViewModel<BaseItemViewModel, Any>

    init {
        itemsVM = SimplePagedListViewModel(
            MinerItemMapper(currencyProvider),
            MinerLoader(params, minerManager),
            minerBindingHelper,
            minerAdapter
        ) as SimplePagedListViewModel<BaseItemViewModel, Any>

        initCoinValue()
    }

    val seekBarLiveData = Transformations.map(seekBarVM.seekBarValueLiveData) { value ->
        GlobalScope.launch(Dispatchers.Default) {
            val usd = value / 100
            setProfitValue(usd)
        }

        GlobalScope.launch(Dispatchers.Main) {
            refreshMinersList()
            minerAdapter.setPowerCost(seekBarVM.getSeekText(value))
        }
    }

    private fun setCoin(coin: CoinDto) {
        coinInfo = coin
        jsonDataStorage.put(COIN_TAG, coin)
        minerAdapter.coinInfo = coinInfo
    }

    private fun refreshMinersList() {
        itemsVM.pagedRecyclerAdapter.notifyItemRangeChanged(1, itemsVM.pagedRecyclerAdapter.itemCount)
    }

    private fun setProfitValue(value: Float) {
        itemsVM.pagedRecyclerAdapter.currentList?.forEach {
            (it as MinerItemViewModel).initPowerCost(value)
        }
    }

    private fun setCoinValue(value: Float) {
        itemsVM.pagedRecyclerAdapter.currentList?.forEach {
            (it as MinerItemViewModel).initCoin(value)
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
            val coin = coinManager.getCoin()
            if (coin.success) {
                setCoin(coin.data!!)
                setCoinValue(coin.data?.price ?: 0f)
                jsonDataStorage.getJson(COIN_TAG, "")
            }
        }
    }
}