package com.sigmapool.app.screens.settings.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.login.data.AUTH_KEY
import com.sigmapool.app.utils.storages.JsonDataStorage
import com.sigmapool.common.models.AuthDto
import com.sigmapool.common.viewModels.ITitleViewModel
import org.kodein.di.generic.instance


class CoinToolbarVM : ViewModel(), ITitleViewModel {

    private val resProvider by kodein.instance<IResProvider>()
    private val jsonDataStorage: JsonDataStorage by kodein.instance()

    val titleLiveData = MutableLiveData(resProvider.getString(R.string.demo))

    val coinText = MutableLiveData("BTC")//FIXME
    val coinIconRes = MutableLiveData(R.mipmap.ic_btc)//FIXME

    init {
        initTitle()
    }

    private fun initTitle() {
        val authDto = Gson().fromJson(jsonDataStorage.getJson(AUTH_KEY), AuthDto::class.java)
        if (authDto != null) {
            titleLiveData.postValue(authDto.username)
        }
    }

    fun onCoinSelected(coin: String) {
        //TODO implement
    }

    override fun getTitle(): LiveData<String> = titleLiveData
}