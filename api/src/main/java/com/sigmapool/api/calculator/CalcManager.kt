package com.sigmapool.api.calculator

import com.sigmapool.api.providers.IApiServiceProvider
import com.sigmapool.common.managers.ICalcManager
import com.sigmapool.common.models.CalcDto
import com.sigmapool.common.models.ManagerResult

internal class CalcManager(serviceProvider: IApiServiceProvider) : ICalcManager {

    val api = serviceProvider.create(CalcApi::class.java)

    override suspend fun getCalcInfo(lang: String): ManagerResult<CalcDto> = try {
        ManagerResult(CalcDto(api.getCalcInfo(lang).payload?.calculatorText ?: ""))
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }
}