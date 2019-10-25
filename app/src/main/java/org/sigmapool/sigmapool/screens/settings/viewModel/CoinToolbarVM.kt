package org.sigmapool.sigmapool.screens.settings.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import org.kodein.di.generic.instance
import org.sigmapool.common.models.AuthDto
import org.sigmapool.common.viewModels.ITitleViewModel
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.coin.ICoinProvider
import org.sigmapool.sigmapool.provider.res.IResProvider
import org.sigmapool.sigmapool.screens.login.data.AUTH_KEY
import org.sigmapool.sigmapool.utils.storages.JsonDataStorage

class CoinToolbarVM : ViewModel(), ITitleViewModel {

    private val resProvider by kodein.instance<IResProvider>()
    private val jsonDataStorage by kodein.instance<JsonDataStorage>()

    val coinProvider by kodein.instance<ICoinProvider>()
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