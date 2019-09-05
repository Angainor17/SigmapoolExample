package com.sigmapool.api.login

import com.sigmapool.api.login.models.LogoutResponse
import com.sigmapool.api.models.AuthResult
import com.sigmapool.api.models.PayloadModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface LoginApi {

    @FormUrlEncoded
    @POST("api/v2/signin")
    suspend fun login(
        @Field("email") login: String,
        @Field("password") password: String
    ): PayloadModel<AuthResult>


    @POST("api/v2/logout")
    suspend fun logout(): PayloadModel<LogoutResponse>

}