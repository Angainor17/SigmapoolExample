package com.sigmapool.api.providers

import com.sigmapool.api.BASE_URL
import com.sigmapool.api.retrofit.HeaderMapper
import com.sigmapool.api.retrofit.createRetrofit

interface IApiServiceProvider {
    fun <T> create(service: Class<T>): T
}

class ServiceProvider(headerMapper: ArrayList<HeaderMapper>) : IApiServiceProvider {
    override fun <T> create(service: Class<T>): T = retrofit.create(service)

    private val retrofit = createRetrofit(
        "https://$BASE_URL/",
        headerMapper
    )
}