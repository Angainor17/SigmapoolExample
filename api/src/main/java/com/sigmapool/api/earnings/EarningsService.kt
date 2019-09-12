package com.sigmapool.api.earnings

import com.sigmapool.api.providers.IApiServiceProvider

internal class EarningsService(apiProvider: IApiServiceProvider) : IEarningsService {

    private val api = apiProvider.create(EarningsApi::class.java)

    override suspend fun earningsDaily(coin: String) = api.earningsDaily(coin).payload!!

    override suspend fun totalPaid(coin: String) = api.totalPaid(coin).payload!!

    override suspend fun balance(coin: String) = api.balance(coin).payload!!

    override suspend fun payments(coin: String) = api.payments(coin).payload!!.payments
}