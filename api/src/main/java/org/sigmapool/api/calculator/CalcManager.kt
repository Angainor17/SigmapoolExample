package org.sigmapool.api.calculator

import org.sigmapool.api.providers.IApiServiceProvider
import org.sigmapool.common.managers.ICalcManager
import org.sigmapool.common.models.CalcDto
import org.sigmapool.common.models.ManagerResult

internal class CalcManager(serviceProvider: IApiServiceProvider) : ICalcManager {

    val api = serviceProvider.create(CalcApi::class.java)

    override suspend fun getCalcInfo(coin: String, lang: String): ManagerResult<CalcDto> = try {
        ManagerResult(CalcDto(api.getCalcInfo(coin, lang).payload?.calculatorText ?: ""))
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }
}