package com.sigmapool.app.screens.settings.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.common.viewModels.ITitleViewModel
import org.kodein.di.generic.instance


class SettingsToolbarVM : ViewModel(), ITitleViewModel {

    private val resProvider by kodein.instance<IResProvider>()

    val titleLiveData = MutableLiveData(resProvider.getString(R.string.demo))

    val coinText = MutableLiveData("BTC")
    val coinIconRes = MutableLiveData(R.mipmap.ic_btc)

    fun onCoinSelected(coin: String) {
        //TODO implement
    }

    override fun getTitle(): LiveData<String> = titleLiveData
}