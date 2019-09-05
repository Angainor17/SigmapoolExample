package com.sigmapool.app.screens.settings.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.coin.ICoinProvider
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.home.coin.CoinVm
import com.sigmapool.app.screens.login.data.AUTH_KEY
import com.sigmapool.app.utils.storages.JsonDataStorage
import com.sigmapool.common.models.AuthDto
import com.sigmapool.common.viewModels.ITitleViewModel
import org.kodein.di.generic.instance


class CoinToolbarVM : ViewModel(), ITitleViewModel, ICoinProvider {

    private val resProvider by kodein.instance<IResProvider>()
    private val jsonDataStorage: JsonDataStorage by kodein.instance()

    private val ltcCoin = CoinVm(resProvider.getString(R.string.ltc), R.mipmap.ic_ltc)
    private val btcCoin = CoinVm(resProvider.getString(R.string.btc), R.mipmap.ic_btc)
    private var selectedCoin = btcCoin
    val coins = arrayListOf(btcCoin, ltcCoin)

    val titleLiveData = MutableLiveData(resProvider.getString(R.string.demo))

    private var listener: ((String) -> Unit)? = null

    init {
        initTitle()
    }

    override fun getLabel(): String = selectedCoin.text

    override fun addChangeListener(listener: (String) -> Unit) {
        this.listener = listener
    }

    fun coinProvider(): ICoinProvider = this

    private fun initTitle() {
        val authDto = Gson().fromJson(jsonDataStorage.getJson(AUTH_KEY), AuthDto::class.java)
        if (authDto != null) {
            titleLiveData.postValue(authDto.username)
        }
    }

    fun onCoinSelected(coin: CoinVm) {
        selectedCoin = coin
        listener?.invoke(coin.text)
    }

    override fun getTitle(): LiveData<String> = titleLiveData
}