package com.sigmapool.app.utils.vm

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sigmapool.api.kodein.AUTH_MODE
import com.sigmapool.api.kodein.OBSERVER_MODE
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.screens.login.data.AUTH_KEY
import com.sigmapool.app.utils.storages.JsonDataStorage
import com.sigmapool.common.models.AuthDto
import org.kodein.di.generic.instance

open class AuthVm : ErrorHandleVm() {

    private val jsonDataStorage by kodein.instance<JsonDataStorage>()

    val isObserverMode = MutableLiveData(false)

    init {
        isObserverMode.postValue(!isLogin())
    }

    private fun isLogin(): Boolean {
        val authDto = Gson().fromJson(jsonDataStorage.getJson(AUTH_KEY), AuthDto::class.java)
        return authDto?.accessToken?.isNotEmpty() ?: false
    }

    fun getManagerMode() = if (isLogin()) AUTH_MODE else OBSERVER_MODE
}