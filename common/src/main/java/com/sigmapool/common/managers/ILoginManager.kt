package com.sigmapool.common.managers

import com.sigmapool.common.models.AuthDto
import com.sigmapool.common.models.ManagerResult


interface ILoginManager {

    suspend fun login(login: String, password: String): ManagerResult<AuthDto>

}