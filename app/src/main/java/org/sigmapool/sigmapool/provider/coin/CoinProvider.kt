package org.sigmapool.sigmapool.provider.coin

import androidx.lifecycle.MutableLiveData
import org.kodein.di.generic.instance
import org.sigmapool.api.kodein.AUTH_MODE
import org.sigmapool.common.managers.IPoolManager
import org.sigmapool.common.models.CoinDto
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.screens.home.coin.CoinVm

private const val INIT_COIN_LIST_INDEX = 0

class CoinProvider : ICoinProvider {

    private val poolManager by kodein.instance<IPoolManager>(AUTH_MODE)

    override val coins = MutableLiveData(CoinStorage.getCoins() ?: ArrayList())

    private var selectedCoin: CoinVm? = null

    private var listeners: ArrayList<((String) -> Unit)?> = ArrayList()

    private fun initList(newList: ArrayList<CoinVm>) {
        if (newList.isNullOrEmpty()) return

        selectedCoin = newList[INIT_COIN_LIST_INDEX]

        coins.postValue(newList)
    }

    override suspend fun init(): Boolean {
        val result = poolManager.getCoins()
        if (result.success) {
            val newList = result.data!!.toCoinVmList()
            CoinStorage.saveCoins(newList)
            initList(newList)
        } else {
            val savedList = CoinStorage.getCoins()
            if (savedList.isNullOrEmpty()) return false

            initList(savedList)
        }
        return true
    }

    override fun getLabel(): String = selectedCoin?.text ?: ""

    override fun addOnChangeListener(listener: (String) -> Unit) {
        listeners.add(listener)
    }

    override fun onCoinSelected(coin: CoinVm) {
        if (selectedCoin != coin) {
            selectedCoin = coin

            listeners.forEach {
                it?.invoke(coin.text)
            }
        }
    }
}

fun ArrayList<CoinDto>.toCoinVmList(): ArrayList<CoinVm> {
    return ArrayList(this.map {
        CoinVm(
            it.code.toUpperCase(),
            it.icon,
            it.unit
        )
    })
}

