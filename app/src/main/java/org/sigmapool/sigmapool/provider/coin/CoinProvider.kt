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

    override val coins = MutableLiveData(ArrayList<CoinVm>())

    private var selectedCoin: CoinVm? = null

    private var listener: ((String) -> Unit)? = null

    private fun initList(newList: ArrayList<CoinVm>) {
        if (!coins.value.isNullOrEmpty()) return

        selectedCoin = newList[INIT_COIN_LIST_INDEX]

        coins.postValue(newList)
    }

    override suspend fun init() {
        val result = poolManager.getCoins()
        if (result.success) {
            result.data!!.toCoinVmList()
        } else {
            thro
        }
        val savedList = CoinStorage.getCoins()
        if (savedList.isNullOrEmpty()) {

        }
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun getCoinsFromCache() = CoinStorage.getCoins()

//    private suspend fun getCoinsFromApi(): ArrayList<CoinVm> {
//        val result = poolManager.getCoins()
//        if (result.success) {
//            val newItems = ArrayList(result.data!!.map {
//                CoinVm(
//                    it.code.toUpperCase(),
//                    it.icon,
//                    it.unit
//                )
//            })
//            CoinStorage.saveCoins(newItems)
//            return newItems
//        }
//        return ArrayList()
//    }

    override fun getLabel(): String = selectedCoin?.text ?: ""

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

fun ArrayList<CoinDto>.toCoinVmList(): ArrayList<CoinVm> {
    return this.map {
        CoinVm(
            it.code.toUpperCase(),
            it.icon,
            it.unit
        )
    }
}

