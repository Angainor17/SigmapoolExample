package org.sigmapool.api.pool

import org.sigmapool.api.providers.IApiServiceProvider

internal class PoolService(apiProvider: IApiServiceProvider) : IPoolService {

    private val api = apiProvider.create(PoolApi::class.java)

    override suspend fun getCoin(coin: String) = api.getCoin(coin).payload!!

    override suspend fun getPayment(coin: String) = api.getPayment(coin).payload!!

    override suspend fun getNetwork(coin: String) = api.getNetwork(coin).payload!!

    override suspend fun getProfitDaily(coin: String) = api.getProfitDaily(coin).payload!!

    override suspend fun getCurrency(coin: String) = api.getCurrency(coin).payload!!

    override suspend fun getScheme(coin: String) = api.getScheme(coin).payload!!

    override suspend fun setScheme(coin: String, scheme: String) =
        api.setScheme(coin, scheme).payload!!

    override suspend fun getThreshold(coin: String) = api.getThreshold(coin).payload!!

    override suspend fun setThreshold(coin: String, threshold: Float) =
        api.setThreshold(coin, threshold).payload!!
}