package org.sigmapool.api.earnings

import org.sigmapool.api.providers.IApiServiceProvider

internal class EarningsService(apiProvider: IApiServiceProvider) : IEarningsService {

    private val api = apiProvider.create(EarningsApi::class.java)

    override suspend fun earningsDaily(coin: String) = api.earningsDaily(coin).payload!!

    override suspend fun totalPaid(coin: String) = api.totalPaid(coin).payload!!

    override suspend fun balance(coin: String) = api.balance(coin).payload!!

    override suspend fun payments(coin: String, page: Int) =
        api.payments(coin, page).payload!!.payments

    override suspend fun getLastPayment(coin: String) = api.getLastPayment(coin).payload!!

    override suspend fun getEstimatedProfit(coin: String) = api.getEstimatedProfit(coin).payload!!

    override suspend fun address(coin: String) = api.address(coin).payload!!
}