package com.sigmapool.api.kodein

import com.sigmapool.api.blog.BlogManager
import com.sigmapool.api.calculator.CalcManager
import com.sigmapool.api.earnings.EarningsManager
import com.sigmapool.api.login.LoginManager
import com.sigmapool.api.miners.MinerManager
import com.sigmapool.api.news.NewsManager
import com.sigmapool.api.pool.PoolManager
import com.sigmapool.api.poolinfo.PoolInfoManager
import com.sigmapool.api.workers.WorkerManager
import com.sigmapool.common.managers.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val managersModule = Kodein.Module("ManagersModule") {
    import(serviceModule)

    bind<IWorkersManager>() with singleton { WorkerManager(instance()) }

    bind<IBlogManager>() with singleton { BlogManager(instance()) }
    bind<IPoolManager>() with singleton { PoolManager(instance()) }
    bind<IMinerManager>() with singleton { MinerManager(instance()) }
    bind<ILoginManager>() with singleton { LoginManager(instance()) }
    bind<INewsManager>() with singleton { NewsManager(instance()) }
    bind<IPoolInfoManager>() with singleton { PoolInfoManager(instance()) }
    bind<ICalcManager>() with singleton { CalcManager(instance()) }
    bind<IEarningsManager>() with singleton { EarningsManager(instance()) }
}