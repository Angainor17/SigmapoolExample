package com.sigmapool.api.kodein

import com.sigmapool.api.blog.BlogService
import com.sigmapool.api.blog.IBlogService
import com.sigmapool.api.dashboard.DashboardService
import com.sigmapool.api.dashboard.IDashboardService
import com.sigmapool.api.earnings.EarningsService
import com.sigmapool.api.earnings.IEarningsService
import com.sigmapool.api.miners.IMinerService
import com.sigmapool.api.miners.MinerService
import com.sigmapool.api.news.INewsService
import com.sigmapool.api.news.NewsService
import com.sigmapool.api.pool.IPoolService
import com.sigmapool.api.pool.PoolService
import com.sigmapool.api.providers.IApiServiceProvider
import com.sigmapool.api.providers.ServiceProvider
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val serviceModule = Module("ServiceModule") {

    bind<IApiServiceProvider>() with singleton { ServiceProvider(instance()) }

    bind<IBlogService>() with singleton { BlogService(instance()) }
    bind<IPoolService>() with singleton { PoolService(instance()) }
    bind<IMinerService>() with singleton { MinerService(instance()) }
    bind<INewsService>() with singleton { NewsService(instance()) }
    bind<IEarningsService>() with singleton { EarningsService(instance()) }
    bind<IDashboardService>() with singleton { DashboardService(instance()) }
}
