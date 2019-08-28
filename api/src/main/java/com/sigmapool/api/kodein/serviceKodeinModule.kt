package com.sigmapool.api.kodein

import com.sigmapool.api.blog.BlogService
import com.sigmapool.api.blog.IBlogService
import com.sigmapool.api.login.ILoginService
import com.sigmapool.api.login.LoginService
import com.sigmapool.api.miners.IMinerService
import com.sigmapool.api.miners.MinerService
import com.sigmapool.api.news.INewsService
import com.sigmapool.api.news.NewsService
import com.sigmapool.api.pool.IPoolService
import com.sigmapool.api.pool.PoolService
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val serviceModule = Module("ServiceModule") {

    bind<IBlogService>() with singleton { BlogService(instance()) }
    bind<IPoolService>() with singleton { PoolService(instance()) }
    bind<IMinerService>() with singleton { MinerService(instance()) }
    bind<ILoginService>() with singleton { LoginService(instance()) }
    bind<INewsService>() with singleton { NewsService(instance()) }
}
