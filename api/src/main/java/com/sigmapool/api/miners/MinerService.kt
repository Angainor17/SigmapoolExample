package com.sigmapool.api.miners

import com.sigmapool.api.models.Miner
import com.sigmapool.api.providers.IApiServiceProvider


internal class MinerService(apiProvider: IApiServiceProvider) : IMinerService {

    private val api = apiProvider.create(MinerApi::class.java)

    override suspend fun getMiners(page: Int, perPage: Int): List<Miner> {
        return api.getMiners(page, perPage).payload?.miners!!.toList()
    }
}