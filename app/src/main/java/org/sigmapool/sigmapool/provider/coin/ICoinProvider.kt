package org.sigmapool.sigmapool.provider.coin

import androidx.lifecycle.MutableLiveData
import org.sigmapool.sigmapool.screens.home.coin.CoinVm

interface ICoinProvider {

    val coins: MutableLiveData<ArrayList<CoinVm>>

    fun getLabel(): String

    fun addOnChangeListener(listener: (String) -> Unit)

    fun onCoinSelected(coin: CoinVm)

    suspend fun init(): Boolean

}