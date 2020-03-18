package org.sigmapool.api.poolinfo

import org.sigmapool.api.providers.IApiServiceProvider
import org.sigmapool.common.managers.IPoolInfoManager
import org.sigmapool.common.models.*


internal class PoolInfoManager(serviceProvider: IApiServiceProvider) : IPoolInfoManager {

    override suspend fun getSettlementDetails(
        coin: String,
        lang: String
    ): ManagerResult<SettlementDetailsDto> {
        return wrapManagerResult {
            val settlementDetails = this.poolInfoService.getSettlementDetails(coin, lang).payload!!

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
                TimeIntervalDto(paymentTime.time.from, paymentTime.time.to),
                paymentTime.min
            )

            paymentTimeDto
        }
    }

    private val poolInfoService = serviceProvider.create(PoolInfoApi::class.java)

    // There is two functions get*tcPoolInfo b/c they returns different classes
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

    override suspend fun getDailyProfit(coin: String): ManagerResult<DailyProfitDto> {
        return wrapManagerResult {
            val dailyProfit =
                this.poolInfoService.getDailyProfit(coin).payload!! // TODO: dehardcode "btc" string

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


