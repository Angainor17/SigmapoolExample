package com.sigmapool.api.kodein

import com.sigmapool.api.BASE_URL
import com.sigmapool.api.login.LoginManager
import com.sigmapool.api.miners.MinerManager
import com.sigmapool.api.providers.BTCServiceProvider
import com.sigmapool.api.providers.IApiServiceProvider
import com.sigmapool.api.providers.LTCServiceProvider
import com.sigmapool.api.retrofit.createRetrofit
import com.sigmapool.common.managers.ILoginManager
import com.sigmapool.common.managers.IMinerManager
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

val managersModule = Kodein.Module("ManagersModule") {

    import(serviceModule)

    bind<Retrofit>(BTC) with singleton {
        createRetrofit(
            "http://$BTC.$BASE_URL/",
            arrayListOf(instance())
        )
    }

    bind<Retrofit>(LTC) with singleton {
        createRetrofit(
            "http://$LTC.$BASE_URL/",
            arrayListOf(instance())
        )
    }

    bind<IApiServiceProvider>(BTC) with singleton { BTCServiceProvider(instance()) }
    bind<IApiServiceProvider>(LTC) with singleton { LTCServiceProvider(instance()) }

    bind<IMinerManager>() with singleton { MinerManager(instance()) }

    bind<ILoginManager>() with singleton { LoginManager(instance(BTC)) }

}
