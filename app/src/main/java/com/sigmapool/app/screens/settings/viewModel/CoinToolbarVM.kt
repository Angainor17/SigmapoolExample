package com.sigmapool.app.screens.settings.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.coin.CoinProvider
import com.sigmapool.app.provider.coin.ICoinProvider
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.login.data.AUTH_KEY
import com.sigmapool.app.utils.storages.JsonDataStorage
import com.sigmapool.common.models.AuthDto
import com.sigmapool.common.viewModels.ITitleViewModel
import org.kodein.di.generic.instance

class CoinToolbarVM : ViewModel(), ITitleViewModel {

    private val resProvider by kodein.instance<IResProvider>()
    private val jsonDataStorage by kodein.instance<JsonDataStorage>()

    val coinProvider: ICoinProvider = CoinProvider()
    val titleLiveData = MutableLiveData(resProvider.getString(R.string.demo))

    init {
        initTitle()
    }

    private fun initTitle() {
        val authDto = Gson().fromJson(jsonDataStorage.getJson(AUTH_KEY), AuthDto::class.java)
        if (authDto != null) {
            titleLiveData.postValue(authDto.username)
        }
    }

    override fun getTitle(): LiveData<String> = titleLiveData
}