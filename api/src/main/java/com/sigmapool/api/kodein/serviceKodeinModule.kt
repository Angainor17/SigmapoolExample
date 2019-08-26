package com.sigmapool.api.kodein

import com.sigmapool.api.blog.BlogService
import com.sigmapool.api.blog.IBlogService
import com.sigmapool.api.login.ILoginService
import com.sigmapool.api.login.LoginService
import com.sigmapool.api.miners.IMinerService
import com.sigmapool.api.miners.StubMinerService
import com.sigmapool.api.pool.IPoolService
import com.sigmapool.api.pool.PoolService
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

const val BTC = "btc"
const val LTC = "ltc"

internal val serviceModule = Module("ServiceModule") {

    bind<IBlogService>() with singleton { BlogService(instance(BTC)) }
    bind<IPoolService>(BTC) with singleton { PoolService(instance(BTC)) }
    bind<IPoolService>(LTC) with singleton { PoolService(instance(LTC)) }
    bind<IMinerService>() with singleton { StubMinerService(instance(BTC)) }
    bind<ILoginService>() with singleton { LoginService(instance(BTC)) }
}
