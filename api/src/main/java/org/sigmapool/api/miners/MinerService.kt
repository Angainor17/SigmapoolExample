package org.sigmapool.api.miners

import org.sigmapool.api.models.Miner
import org.sigmapool.api.providers.IApiServiceProvider


internal class MinerService(apiProvider: IApiServiceProvider) : IMinerService {

    private val api = apiProvider.create(MinerApi::class.java)

    override suspend fun getMiners(page: Int, perPage: Int): List<Miner> {
        return api.getMiners(page, perPage).payload?.miners!!.toList()
    }
}