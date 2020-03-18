package org.sigmapool.common.managers

import org.sigmapool.common.models.AuthDto
import org.sigmapool.common.models.LogoutDto
import org.sigmapool.common.models.ManagerResult


interface ILoginManager {

    suspend fun login(login: String, password: String): ManagerResult<AuthDto>

    suspend fun logout(): ManagerResult<LogoutDto>

}