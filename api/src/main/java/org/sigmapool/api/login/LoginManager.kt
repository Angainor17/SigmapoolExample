package org.sigmapool.api.login

import org.sigmapool.api.providers.IApiServiceProvider
import org.sigmapool.common.managers.ILoginManager
import org.sigmapool.common.models.AuthDto
import org.sigmapool.common.models.LogoutDto
import org.sigmapool.common.models.ManagerResult

internal class LoginManager(serviceProvider: IApiServiceProvider) : ILoginManager {

    private val loginService = serviceProvider.create(LoginApi::class.java)

    override suspend fun login(login: String, password: String): ManagerResult<AuthDto> = try {
        val authInfo = loginService.login(login, password).payload!!

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

    override suspend fun logout(): ManagerResult<LogoutDto> = try {
        ManagerResult(LogoutDto(loginService.logout().payload!!.message))
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }
}
