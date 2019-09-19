package com.sigmapool.api.kodein

import com.sigmapool.api.blog.BlogManager
import com.sigmapool.api.calculator.CalcManager
import com.sigmapool.api.chart.ChartManager
import com.sigmapool.api.dashboard.DashboardManager
import com.sigmapool.api.dashboard.StubDashboardManager
import com.sigmapool.api.earnings.EarningsManager
import com.sigmapool.api.earnings.StubEarningsManager
import com.sigmapool.api.login.LoginManager
import com.sigmapool.api.miners.MinerManager
import com.sigmapool.api.news.NewsManager
import com.sigmapool.api.pool.PoolManager
import com.sigmapool.api.pool.StubPoolManager
import com.sigmapool.api.poolinfo.PoolInfoManager
import com.sigmapool.api.workers.StubWorkerManager
import com.sigmapool.api.workers.WorkerManager
import com.sigmapool.common.managers.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

const val OBSERVER_MODE = 0
const val AUTH_MODE = 1

val managersModule = Kodein.Module("ManagersModule") {
    import(serviceModule)

    bind<IWorkersManager>(OBSERVER_MODE) with singleton { StubWorkerManager(instance()) }
    bind<IWorkersManager>(AUTH_MODE) with singleton { WorkerManager(instance()) }

    bind<IPoolManager>(OBSERVER_MODE) with singleton { StubPoolManager(instance()) }
    bind<IPoolManager>(AUTH_MODE) with singleton { PoolManager(instance()) }

    bind<IEarningsManager>(OBSERVER_MODE) with singleton { StubEarningsManager(instance()) }
    bind<IEarningsManager>(AUTH_MODE) with singleton { EarningsManager(instance()) }

    bind<IDashboardManager>(OBSERVER_MODE) with singleton { StubDashboardManager(instance()) }
    bind<IDashboardManager>(AUTH_MODE) with singleton { DashboardManager(instance()) }

    bind<IBlogManager>() with singleton { BlogManager(instance()) }
    bind<IMinerManager>() with singleton { MinerManager(instance()) }
    bind<ILoginManager>() with singleton { LoginManager(instance()) }
    bind<INewsManager>() with singleton { NewsManager(instance()) }
    bind<IPoolInfoManager>() with singleton { PoolInfoManager(instance()) }
    bind<ICalcManager>() with singleton { CalcManager(instance()) }
    bind<IChartManager>() with singleton { ChartManager(instance()) }
}