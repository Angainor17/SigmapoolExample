package com.sigmapool.api.poolinfo

import com.sigmapool.api.providers.IApiServiceProvider
import com.sigmapool.common.managers.IPoolInfoManager
import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.PoolInfoBtcDto

internal class PoolInfoManager(serviceProvider: IApiServiceProvider) : IPoolInfoManager {

    private val poolInfoService = serviceProvider.create(PoolInfoApi::class.java)

    override suspend fun getPoolInfo(): ManagerResult<PoolInfoBtcDto> = try {

        val poolInfo = poolInfoService.getPoolInfo().payload!!

        val poolInfoDto = PoolInfoBtcDto(
            poolInfo.feePps,
            poolInfo.feeFpps,
            poolInfo.settlementTime,
            poolInfo.addressChangeTimeout,
            poolInfo.stratumURLs
        )

        ManagerResult(poolInfoDto)
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }
}
