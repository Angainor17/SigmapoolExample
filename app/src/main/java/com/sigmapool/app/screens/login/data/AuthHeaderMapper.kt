package com.sigmapool.app.screens.login.data

import android.util.Log
import com.google.gson.Gson
import com.sigmapool.api.retrofit.AUTHORIZATION
import com.sigmapool.api.retrofit.BEARER
import com.sigmapool.api.retrofit.HeaderMapper
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.utils.eventBus.LogoutEvent
import com.sigmapool.app.utils.storages.JsonDataStorage
import com.sigmapool.common.models.AuthDto
import okhttp3.Response
import org.greenrobot.eventbus.EventBus
import org.kodein.di.generic.instance
import java.net.HttpURLConnection.HTTP_BAD_REQUEST

const val AUTH_KEY = "auth_info"
const val REFRESH_HEADER = "x-sigmapool-access-token"

class AuthHeaderMapper : HeaderMapper() {

    private val jsonDataStorage: JsonDataStorage by kodein.instance()

    override fun getHeaderName(): String = AUTHORIZATION

    override fun getValue(): String {
        val authDto = Gson().fromJson(jsonDataStorage.getJson(AUTH_KEY), AuthDto::class.java)
        return if (authDto != null) {
            BEARER + authDto.accessToken
        } else ""
    }

    override fun responseProceed(response: Response) {
        if (response.code() == HTTP_BAD_REQUEST) {
            val authDto = Gson().fromJson(jsonDataStorage.getJson(AUTH_KEY), AuthDto::class.java)
            if (authDto != null) {
                jsonDataStorage.put(AUTH_KEY, null)
                EventBus.getDefault().post(LogoutEvent())
            }
        }

        val newToken = response.header(REFRESH_HEADER)
        if (newToken != null) {
            val authDto = Gson().fromJson(jsonDataStorage.getJson(AUTH_KEY), AuthDto::class.java)
            authDto.accessToken = newToken
            jsonDataStorage.put(AUTH_KEY, authDto)

            Log.d("voronin", "Token refresh = $newToken")
        }
    }
}