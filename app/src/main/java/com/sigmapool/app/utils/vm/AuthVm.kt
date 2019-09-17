package com.sigmapool.app.utils.vm

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.screens.login.data.AUTH_KEY
import com.sigmapool.app.utils.storages.JsonDataStorage
import com.sigmapool.common.models.AuthDto
import org.kodein.di.generic.instance

open class AuthVm : ErrorHandleVm() {

    private val jsonDataStorage by kodein.instance<JsonDataStorage>()

    val isObserverMode = MutableLiveData(false)

    init {
        val authDto = Gson().fromJson(jsonDataStorage.getJson(AUTH_KEY), AuthDto::class.java)
        isObserverMode.postValue(authDto?.accessToken?.isEmpty() ?: true)
    }
}