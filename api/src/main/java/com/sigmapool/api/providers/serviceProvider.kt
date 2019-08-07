package com.sigmapool.api.providers

import com.sigmapool.api.BASE_URL
import com.sigmapool.api.kodein.BTC
import com.sigmapool.api.kodein.LTC
import com.sigmapool.api.retrofit.HeaderMapper
import com.sigmapool.api.retrofit.createRetrofit

interface IApiServiceProvider {
    fun <T> create(service: Class<T>): T
}

class BTCServiceProvider(headerMapper: HeaderMapper) : IApiServiceProvider {
    override fun <T> create(service: Class<T>): T = retrofit.create(service)

    private val retrofit = createRetrofit(
        "http://$BTC.$BASE_URL/",
        arrayListOf(headerMapper)
    )
}

class LTCServiceProvider(headerMapper: HeaderMapper) : IApiServiceProvider {
    override fun <T> create(service: Class<T>): T = retrofit.create(service)

    private val retrofit = createRetrofit(
        "http://$LTC.$BASE_URL/",
        arrayListOf(headerMapper)
    )
}