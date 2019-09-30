package com.sigmapool.api.dashboard

import android.content.Context
import com.sigmapool.common.managers.IDashboardManager
import com.sigmapool.common.models.AvgDto
import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.SubAccountDto

internal class StubDashboardManager(private val context: Context) : IDashboardManager {

    override suspend fun subaccounts(coin: String): ManagerResult<ArrayList<SubAccountDto>> {
        return ManagerResult(
//            ArrayList()
            ArrayList(List(4) {
                SubAccountDto(
                    getName(it),
                    1230112f - it / 10 * 1000012f,
                    0.03f + it.toFloat() / 100
                )
            }
    )
        )
    }

    private fun getName(index: Int): String = when (index) {
        0 -> "divny01"
        1 -> "dod2"
        2 -> "serej34"
        else -> "soolmo"
    }

    override suspend fun avg(coin: String): ManagerResult<AvgDto> {
        return ManagerResult(
            AvgDto(
                if (coin == "btc") 1231231234124f else 6767656456456456f,
                if (coin == "btc") 7374737171723f else 9123959129123433f
            )
        )
    }
}