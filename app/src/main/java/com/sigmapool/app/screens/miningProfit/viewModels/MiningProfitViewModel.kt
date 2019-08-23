package com.sigmapool.app.screens.miningProfit.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.models.Currency
import com.sigmapool.app.screens.miningProfit.*
import com.sigmapool.app.utils.JsonDataStorage
import com.sigmapool.app.utils.getString
import com.sigmapool.common.listLibrary.pagedlist.SimplePagedListViewModel
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import com.sigmapool.common.managers.IMinerManager
import com.sigmapool.common.managers.IPoolManager
import com.sigmapool.common.models.CoinDto
import com.sigmapool.common.viewModels.ITitleViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

const val COIN_TAG = "coin"
const val GOOGLE_FONT_FAMILY = "Google Sans"//FIXME

class MiningProfitViewModel(val view: IMinerFragmentModel) : ViewModel(), ITitleViewModel, IMiningProfitToolbarViewModel {

    private val usdCurrency = Currency(1, 12, 1, R.array.array_first_and_last_1_12, 0)

    private val currencyLiveData = MutableLiveData(usdCurrency)
    private val seekBarVM = SeekBarVM(currencyLiveData)
    private val minerHeaderVM = MinerHeaderVM(seekBarVM)
    private val minerBindingHelper = MinerBindingHelper()
    private val minerAdapter = MiningListAdapter(minerHeaderVM, minerBindingHelper)

    private val minerManager by kodein.instance<IMinerManager>()
    private val coinManager by kodein.instance<IPoolManager>()
    private val jsonDataStorage by kodein.instance<JsonDataStorage>()

    private var coinInfo: CoinDto? = null
    val itemsVM: SimplePagedListViewModel<BaseItemViewModel, Any>

    init {
        itemsVM = SimplePagedListViewModel(
            MinerItemMapper(),
            MinerLoader(minerManager),
            minerBindingHelper,
            minerAdapter
        ) as SimplePagedListViewModel<BaseItemViewModel, Any>

//        currencyLiveData.value = usdCurrency
        initCoinValue()
    }

    val seekBarLiveData = map(seekBarVM.seekBarValueLiveData) { value ->
        GlobalScope.launch(Dispatchers.Default) {
            val usd = value / 100
            setProfitValue(usd)
        }

        GlobalScope.launch(Dispatchers.Main) {
            refreshMinersList()
            minerAdapter.setPowerCost(seekBarVM.getSeekText(value))
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

    override fun backBtnClick() {
        view.backBtnClick()
    }

    override fun onProfitBtnSelected(isUpSort: Boolean) {
        //TODO implement sort
//        val listBefore = ArrayList<MinerItemViewModel>()
//        itemsVM.pagedRecyclerAdapter.currentList?.forEach {
//            listBefore.add(it as MinerItemViewModel)
//        }
//
//        val listAfter = listBefore.sortedWith(Comparator { o1, o2 ->
//            (if (isUpSort) -1 else 1) * o1.profitValue.compareTo(o2.profitValue)
//        })
//        val newIndexes = ArrayList<Int>()
//
//        listBefore.forEach {
//            newIndexes.add(listAfter.indexOf(it))
//        }
//
//        newIndexes.forEachIndexed { index, item ->
//            minerAdapter.notifyItemMoved(index + 1, item + 1)
//        }
    }

    override fun getTitle() = MutableLiveData<String>().apply { value = getString(R.string.mining_profit) }

}
