package com.sigmapool.common.managers

import com.sigmapool.common.models.CalcDto
import com.sigmapool.common.models.ManagerResult

interface ICalcManager {
    suspend fun getCalcInfo(): ManagerResult<CalcDto>
}