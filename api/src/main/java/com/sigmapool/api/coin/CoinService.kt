package com.sigmapool.api.coin

import retrofit2.Retrofit

internal class CoinService(retrofit: Retrofit) : ICoinService {

    private val api = retrofit.create(CoinApi::class.java)

    override suspend fun getCoin(): CoinInfo {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}