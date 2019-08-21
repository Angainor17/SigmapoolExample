package com.sigmapool.api.miners

import com.sigmapool.api.models.Miner
import retrofit2.Retrofit


internal class MinerService(retrofit: Retrofit) : IMinerService {

    private val api = retrofit.create(MinerApi::class.java)

    override suspend fun getMiners(page: Int, perPage: Int): List<Miner> {
        return api.getMiners(page, perPage).payload?.miners!!.toList()
    }
}