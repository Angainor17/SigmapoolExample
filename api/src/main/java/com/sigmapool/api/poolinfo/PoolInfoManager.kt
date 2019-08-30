package com.sigmapool.api.poolinfo

import com.sigmapool.api.providers.IApiServiceProvider
import com.sigmapool.common.managers.IPoolInfoManager
import com.sigmapool.common.models.*

internal class PoolInfoManager(serviceProvider: IApiServiceProvider) : IPoolInfoManager {

    override suspend fun getSettlementDetails(coin: String): ManagerResult<SettlementDetailsDto>  = try {
        val settlementDetails = this.poolInfoService.getSettlementDetails(coin).payload!!

        val settlementDetailsDto = SettlementDetailsDto(
            settlementDetails.settlementDetailsText
        )

        ManagerResult(settlementDetailsDto)
    } catch(e:Throwable){
        ManagerResult(error = e.message)
    }

    override suspend fun getPayment(coin: String): ManagerResult<PaymentDto> = try {
        val paymentTime = this.poolInfoService.getPayment(coin).payload!!

        val paymentTimeDto = PaymentDto(
            TimeIntervalDto(paymentTime.time.from, paymentTime.time.to),
            paymentTime.min
        )

        ManagerResult(paymentTimeDto)
    }catch (e:Throwable) {
        ManagerResult(error = e.message)
    }


    private val poolInfoService = serviceProvider.create(PoolInfoApi::class.java)

    // TODO signle function with "coin" parameter
    override suspend fun getBtcPoolInfo(): ManagerResult<PoolInfoBtcDto> = try {

        val poolInfo = this.poolInfoService.getBtcPoolInfo().payload!!

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

    override suspend fun getDailyProfit(coin:String): ManagerResult<DailyProfitDto> {
        return try {
            val dailyProfit = this.poolInfoService.getDailyProfit(coin).payload!! // TODO: dehardcode "btc" string

            val dailyProfitDto = DailyProfitDto(
                dailyProfit.profit
            )

            ManagerResult(dailyProfitDto)
        } catch (e: Throwable) {
            ManagerResult(error = e.message)
        }
    }


    override suspend fun getLtcPoolInfo(): ManagerResult<PoolInfoLtcDto> = try {

        val poolInfo = this.poolInfoService.getLtcPoolInfo().payload!!

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
