package com.sigmapool.api.kodein

import com.sigmapool.api.blog.StubBlogManager
import com.sigmapool.api.calculator.CalcManager
import com.sigmapool.api.login.LoginManager
import com.sigmapool.api.miners.MinerManager
import com.sigmapool.api.news.NewsManager
import com.sigmapool.api.pool.StubPoolManager
import com.sigmapool.api.poolinfo.PoolInfoManager
import com.sigmapool.api.workers.StubWorkerManager
import com.sigmapool.common.managers.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val managersModule = Kodein.Module("ManagersModule") {
    import(serviceModule)

    bind<IWorkersManager>() with singleton { StubWorkerManager(instance()) }

    bind<IBlogManager>() with singleton { StubBlogManager(instance()) }
    bind<IPoolManager>() with singleton { StubPoolManager(instance()) }
    bind<IMinerManager>() with singleton { MinerManager(instance()) }
    bind<ILoginManager>() with singleton { LoginManager(instance()) }
    bind<INewsManager>() with singleton { NewsManager(instance()) }
    bind<IPoolInfoManager>() with singleton { PoolInfoManager(instance()) }
    bind<ICalcManager>() with singleton { CalcManager(instance()) }
}