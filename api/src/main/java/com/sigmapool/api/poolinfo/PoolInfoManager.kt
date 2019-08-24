package com.sigmapool.api.poolinfo

import com.sigmapool.api.providers.IApiServiceProvider
import com.sigmapool.common.managers.IPoolInfoManager
import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.PoolInfoBtcDto
import com.sigmapool.common.models.PoolInfoLtcDto

internal class PoolInfoManager(btcServiceProvider: IApiServiceProvider, ltcServiceProvider: IApiServiceProvider) : IPoolInfoManager {

    private val btcPoolInfoService = btcServiceProvider.create(PoolInfoApi::class.java)
    private val ltcPoolInfoService = ltcServiceProvider.create(PoolInfoApi::class.java)

    override suspend fun getBtcPoolInfo(): ManagerResult<PoolInfoBtcDto> = try {

        val poolInfo = btcPoolInfoService.getBtcPoolInfo().payload!!

        val poolInfoBtcDto = PoolInfoBtcDto(
            poolInfo.fee.pps,
            poolInfo.fee.fpps,
            poolInfo.settlementTime,
            poolInfo.addressChangeTimeout,
            poolInfo.stratumURLs
        )

        ManagerResult(poolInfoBtcDto)
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }

    override suspend fun getLtcPoolInfo(): ManagerResult<PoolInfoLtcDto> = try {

        val poolInfo = ltcPoolInfoService.getLtcPoolInfo().payload!!

        val poolInfoLtcDto = PoolInfoLtcDto(
            poolInfo.fee.pps,
            poolInfo.settlementTime,
            poolInfo.addressChangeTimeout,
            poolInfo.stratumURLs
        )

        ManagerResult(poolInfoLtcDto)
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }
}
