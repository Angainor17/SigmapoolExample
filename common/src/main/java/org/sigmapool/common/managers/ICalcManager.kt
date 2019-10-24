package org.sigmapool.common.managers

import org.sigmapool.common.models.CalcDto
import org.sigmapool.common.models.ManagerResult

interface ICalcManager {
    suspend fun getCalcInfo(coin: String, lang: String): ManagerResult<CalcDto>
}