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

class CoinProvider : ICoinProvider {

    private val poolManager by kodein.instance<IPoolManager>(AUTH_MODE)

    override val coins = MutableLiveData(ArrayList<CoinVm>())

    private var selectedCoin: CoinVm? = null

    private var listener: ((String) -> Unit)? = null

    init {
        getCoinsFromApi()
        getCoinsFromCache()
    }

    private fun getCoinsFromCache() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun getCoinsFromApi() {
        GlobalScope.launch(Dispatchers.Default) {
            val result = poolManager.getCoins()//FIXME add cache
            if (result.success) {
                coins.postValue(ArrayList(result.data!!.map {
                    CoinVm(
                        it.code.toUpperCase(),
                        it.icon,
                        it.unit
                    )
                }))

            }
        }
    }

    override fun getLabel(): String = selectedCoin?.text ?: ""

    override suspend fun getLabelAwait(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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