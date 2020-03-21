package org.sigmapool.api.poolinfo

import org.sigmapool.api.providers.IApiServiceProvider
import org.sigmapool.common.managers.IPoolInfoManager
import org.sigmapool.common.models.*


internal class PoolInfoManager(serviceProvider: IApiServiceProvider) : IPoolInfoManager {

    private val poolInfoService = serviceProvider.create(PoolInfoApi::class.java)

    override suspend fun getSettlementDetails(
        coin: String,
        lang: String
    ): ManagerResult<SettlementDetailsDto> {
        return wrapManagerResult {
            val settlementDetails = this.poolInfoService.getSettlementDetails(coin, lang).payload!!

            SettlementDetailsDto(
                settlementDetails.settlementDetailsText
            )
        }
    }

    override suspend fun getPayment(coin: String): ManagerResult<PaymentDto> {
        return wrapManagerResult {
            val paymentTime = this.poolInfoService.getPayment(coin).payload!!

            PaymentDto(
                TimeIntervalDto(paymentTime.time.from, paymentTime.time.to),
                paymentTime.min
            )
        }
    }

    override suspend fun getPoolInfo(coin: String): ManagerResult<PoolInfoCoinDto> {
        return wrapManagerResult {
            val poolInfo = this.poolInfoService.getPoolInfo(coin).payload!!

            PoolInfoCoinDto(
                poolInfo.fee.pps,
                poolInfo.fee.fpps,
                poolInfo.fee.solo,
                poolInfo.fee.pplns,
                poolInfo.settlementTime,
                poolInfo.addressChangeTimeout,
                poolInfo.stratumURLs
            )
        }
    }

    override suspend fun getDailyProfit(coin: String): ManagerResult<DailyProfitDto> {
        return wrapManagerResult {
            val dailyProfit = this.poolInfoService.getDailyProfit(coin).payload!!
            DailyProfitDto(dailyProfit.profit)
        }
    }
}


