package com.sigmapool.api.login

import com.sigmapool.api.models.AuthResult
import com.sigmapool.api.models.PayloadModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface LoginApi {

    @FormUrlEncoded
    @POST("api/v2/btc/signin")
    suspend fun login(
        @Field("email") login: String,
        @Field("password") password: String
    ): PayloadModel<AuthResult>

}