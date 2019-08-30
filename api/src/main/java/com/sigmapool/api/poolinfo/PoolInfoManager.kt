package com.sigmapool.api.poolinfo

import com.sigmapool.api.providers.IApiServiceProvider
import com.sigmapool.api.wrapManagerResult
import com.sigmapool.common.managers.IPoolInfoManager
import com.sigmapool.common.models.*


internal class PoolInfoManager(serviceProvider: IApiServiceProvider) : IPoolInfoManager {

    override suspend fun getSettlementDetails(coin: String): ManagerResult<SettlementDetailsDto> {
        return wrapManagerResult {
            val settlementDetails = this.poolInfoService.getSettlementDetails(coin).payload!!

            val settlementDetailsDto = SettlementDetailsDto(
                settlementDetails.settlementDetailsText
            )

            settlementDetailsDto
        }
    }

    override suspend fun getPayment(coin: String): ManagerResult<PaymentDto> {
        return wrapManagerResult {
            val paymentTime = this.poolInfoService.getPayment(coin).payload!!

            val paymentTimeDto = PaymentDto(
                PaymentTime(paymentTime.time.from, paymentTime.time.to),
                paymentTime.min
            )

            paymentTimeDto
        }
    }

    private val poolInfoService = serviceProvider.create(PoolInfoApi::class.java)

    // TODO signle function with "coin" parameter
    override suspend fun getBtcPoolInfo(): ManagerResult<PoolInfoBtcDto> {
        return wrapManagerResult {

            val poolInfo = this.poolInfoService.getBtcPoolInfo().payload!!

            val poolInfoBtcDto = PoolInfoBtcDto(
                poolInfo.fee.pps,
                poolInfo.fee.fpps,
                poolInfo.settlementTime,
                poolInfo.addressChangeTimeout,
                poolInfo.stratumURLs
            )

            poolInfoBtcDto
        }
    }

    override suspend fun getDailyProfit(coin:String): ManagerResult<DailyProfitDto> {
        return wrapManagerResult {
            val dailyProfit = this.poolInfoService.getDailyProfit(coin).payload!! // TODO: dehardcode "btc" string

            val dailyProfitDto = DailyProfitDto(
                dailyProfit.profit
            )

            dailyProfitDto
        }
    }

    override suspend fun getLtcPoolInfo(): ManagerResult<PoolInfoLtcDto> {
        return wrapManagerResult {

            val poolInfo = this.poolInfoService.getLtcPoolInfo().payload!!

            val poolInfoLtcDto = PoolInfoLtcDto(
                poolInfo.fee.pps,
                poolInfo.settlementTime,
                poolInfo.addressChangeTimeout,
                poolInfo.stratumURLs
            )

            poolInfoLtcDto
        }
    }
}
