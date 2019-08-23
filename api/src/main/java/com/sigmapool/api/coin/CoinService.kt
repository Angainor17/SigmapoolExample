package com.sigmapool.api.coin

import retrofit2.Retrofit

internal class CoinService(retrofit: Retrofit) : ICoinService {

    private val api = retrofit.create(CoinApi::class.java)

    override suspend fun getCoin(): CoinResponse {
        return api.getCoin().payload!!
    }
}