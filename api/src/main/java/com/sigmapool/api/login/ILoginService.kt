package com.sigmapool.api.login

import com.sigmapool.api.models.AuthResult


internal interface ILoginService {
    suspend fun login(login: String, password: String): AuthResult?
}

