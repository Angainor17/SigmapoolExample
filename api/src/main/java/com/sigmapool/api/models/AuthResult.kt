package com.sigmapool.api.models

import com.google.gson.annotations.SerializedName

class AuthResult {

    val id: String = ""

    val username: String = ""

    val email: String = ""

    val anonymous: Boolean = false

    val created: String = ""

    val refid: String = ""

    val referrer: String = ""

    val activated: Boolean = false

    @SerializedName("two_factor_enabled")
    val twoFactorEnabled: String = ""

    @SerializedName("notify_workers_offline")
    val notifyWorkersOffline: String = ""

    val apikey: String = ""

    @SerializedName("issubaccount")
    val isSubaccount: String = ""

    val owner: String = ""
    val accessToken: String = ""


}