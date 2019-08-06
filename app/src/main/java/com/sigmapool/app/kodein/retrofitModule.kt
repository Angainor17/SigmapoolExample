package com.sigmapool.app.kodein

import com.sigmapool.api.BASE_URL
import com.sigmapool.api.kodein.BTC
import com.sigmapool.api.kodein.LTC
import com.sigmapool.api.retrofit.createRetrofit
import com.sigmapool.app.screens.login.data.AuthHeaderMapper
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

internal val retrofitModule = Kodein.Module("RetrofitModule") {

    bind<Retrofit>(BTC) with singleton {
        createRetrofit(
            "http://$BTC.$BASE_URL/",
            arrayListOf(AuthHeaderMapper())
        )
    }

    bind<Retrofit>(LTC) with singleton {
        createRetrofit(
            "http://$LTC.$BASE_URL/",
            arrayListOf(AuthHeaderMapper())
        )
    }
}
