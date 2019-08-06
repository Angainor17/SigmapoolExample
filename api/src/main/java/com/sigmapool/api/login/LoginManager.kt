package com.sigmapool.api.login

import com.sigmapool.common.managers.ILoginManager
import com.sigmapool.common.models.AuthDto
import com.sigmapool.common.models.ManagerResult

internal class LoginManager(private val loginService: ILoginService) : ILoginManager {

    override suspend fun login(login: String, password: String): ManagerResult<AuthDto> = try {
        val authInfo = ManagerResult(loginService.login(login, password)).data!!

        val authDto = AuthDto(
            authInfo.id,
            authInfo.username,
            authInfo.email,
            authInfo.accessToken
        )

        ManagerResult(authDto)
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }
}
