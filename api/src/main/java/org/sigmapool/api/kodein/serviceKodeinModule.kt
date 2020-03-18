package org.sigmapool.api.kodein

import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.sigmapool.api.blog.BlogService
import org.sigmapool.api.blog.IBlogService
import org.sigmapool.api.dashboard.DashboardService
import org.sigmapool.api.dashboard.IDashboardService
import org.sigmapool.api.earnings.EarningsService
import org.sigmapool.api.earnings.IEarningsService
import org.sigmapool.api.miners.IMinerService
import org.sigmapool.api.miners.MinerService
import org.sigmapool.api.news.INewsService
import org.sigmapool.api.news.NewsService
import org.sigmapool.api.pool.IPoolService
import org.sigmapool.api.pool.PoolService
import org.sigmapool.api.providers.IApiServiceProvider
import org.sigmapool.api.providers.ServiceProvider

internal val serviceModule = Module("ServiceModule") {

    bind<IApiServiceProvider>() with singleton { ServiceProvider(instance()) }

    bind<IBlogService>() with singleton { BlogService(instance()) }
    bind<IPoolService>() with singleton { PoolService(instance()) }
    bind<IMinerService>() with singleton { MinerService(instance()) }
    bind<INewsService>() with singleton { NewsService(instance()) }
    bind<IEarningsService>() with singleton { EarningsService(instance()) }
    bind<IDashboardService>() with singleton { DashboardService(instance()) }
}
