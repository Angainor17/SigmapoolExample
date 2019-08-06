package com.sigmapool.api.login

import com.sigmapool.api.models.AuthResult
import retrofit2.Retrofit

//TODO remove
internal class LoginService(retrofit: Retrofit) : ILoginService {

    val api: LoginApi = retrofit.create(LoginApi::class.java)

    override suspend fun login(login: String, password: String): AuthResult? {
        return api.login(login, password).payload
    }
}