package com.sigmapool.api.kodein

import com.sigmapool.api.BASE_URL
import com.sigmapool.api.login.LoginManager
import com.sigmapool.api.miners.MinerManager
import com.sigmapool.api.poolinfo.PoolInfoManager
import com.sigmapool.api.providers.IApiServiceProvider
import com.sigmapool.api.providers.ServiceProvider
import com.sigmapool.api.retrofit.createRetrofit
import com.sigmapool.common.managers.ILoginManager
import com.sigmapool.common.managers.IMinerManager
import com.sigmapool.common.managers.IPoolInfoManager
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

val managersModule = Kodein.Module("ManagersModule") {

    import(serviceModule)
    bind<Retrofit>() with singleton {
        createRetrofit(
            "http://$BASE_URL/",
            arrayListOf(instance())
        )
    }

    bind<IApiServiceProvider>() with singleton { ServiceProvider(instance()) }

    bind<IMinerManager>() with singleton { MinerManager(instance()) }
    bind<ILoginManager>() with singleton { LoginManager(instance()) }
    bind<IPoolInfoManager>() with singleton { PoolInfoManager(instance()) }

}
