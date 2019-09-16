package com.sigmapool.api.dashboard

import android.content.Context
import com.sigmapool.api.R
import com.sigmapool.api.hasConnection
import com.sigmapool.common.managers.IDashboardManager
import com.sigmapool.common.models.AvgDto
import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.SubAccountDto
import kotlinx.coroutines.delay

internal class StubDashboardManager(private val context: Context) : IDashboardManager {

    override suspend fun subaccounts(coin: String): ManagerResult<ArrayList<SubAccountDto>> {
        delay(3000)

        if (!hasConnection(context)) return ManagerResult(error = context.getString(R.string.no_connection))

        return ManagerResult(
            ArrayList(List(2) {
                SubAccountDto(
                    "Name $it",
                    1230112f,
                    0.3f
                )
            })
        )
    }

    override suspend fun avg(coin: String): ManagerResult<AvgDto> {
        delay(2000)

        if (!hasConnection(context)) return ManagerResult(error = context.getString(R.string.no_connection))

        return ManagerResult(
            AvgDto(
                1231231234124f,
                6767656456456456f
            )
        )
    }
}