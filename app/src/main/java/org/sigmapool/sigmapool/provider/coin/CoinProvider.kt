package org.sigmapool.sigmapool.provider.coin

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import org.sigmapool.api.kodein.AUTH_MODE
import org.sigmapool.common.managers.IPoolManager
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.screens.home.coin.CoinVm

private const val INIT_COIN_LIST_INDEX = 0

class CoinProvider : ICoinProvider {

    private val poolManager by kodein.instance<IPoolManager>(AUTH_MODE)

    override val coins = MutableLiveData(ArrayList<CoinVm>())

    private var selectedCoin: CoinVm? = null

    private var listener: ((String) -> Unit)? = null

    init {
        initCoinsList()
    }

    private fun initCoinsList() {
        GlobalScope.launch(Dispatchers.IO) {
            val cache = getCoinsFromCache()
            if (!cache.isNullOrEmpty()) {
                initList(cache)
            } else {
                initList(getCoinsFromApi())
            }
        }
    }

    private fun initList(newList: ArrayList<CoinVm>) {
        if (!coins.value.isNullOrEmpty()) return

        selectedCoin = newList[INIT_COIN_LIST_INDEX]

        coins.postValue(newList)
    }

    private fun getCoinsFromCache() = CoinStorage.getCoins()

    private suspend fun getCoinsFromApi(): ArrayList<CoinVm> {
        val result = poolManager.getCoins()
        if (result.success) {
            val newItems = ArrayList(result.data!!.map {
                CoinVm(
                    it.code.toUpperCase(),
                    it.icon,
                    it.unit
                )
            })
            CoinStorage.saveCoins(newItems)
            return newItems
        }
        return ArrayList()
    }

    override fun getLabel(): String = selectedCoin?.text ?: ""

    override suspend fun getLabelAwait(): String {
        initCoinsList()
        return getLabel()
    }

    override fun addOnChangeListener(listener: (String) -> Unit) {
        this.listener = listener
    }

    override fun onCoinSelected(coin: CoinVm) {
        if (selectedCoin != coin) {
            selectedCoin = coin
            listener?.invoke(coin.text)
        }
    }
}