package org.sigmapool.sigmapool.screens.login.data

import android.util.Log
import com.google.gson.Gson
import okhttp3.Response
import org.kodein.di.generic.instance
import org.sigmapool.api.retrofit.AUTHORIZATION
import org.sigmapool.api.retrofit.BEARER
import org.sigmapool.api.retrofit.HeaderMapper
import org.sigmapool.common.models.AuthDto
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.utils.storages.JsonDataStorage

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
        val newToken = response.header(REFRESH_HEADER)
        if (newToken != null) {
            val authDto = Gson().fromJson(jsonDataStorage.getJson(AUTH_KEY), AuthDto::class.java)
            authDto.accessToken = newToken
            jsonDataStorage.put(AUTH_KEY, authDto)

            Log.d("voronin", "Token refresh = $newToken")
        }
    }
}