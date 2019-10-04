package org.sigmapool.sigmapool.utils.vm

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import org.kodein.di.generic.instance
import org.sigmapool.api.kodein.AUTH_MODE
import org.sigmapool.api.kodein.OBSERVER_MODE
import org.sigmapool.common.models.AuthDto
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.screens.login.data.AUTH_KEY
import org.sigmapool.sigmapool.utils.storages.JsonDataStorage

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