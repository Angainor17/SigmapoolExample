package org.sigmapool.api.kodein

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.sigmapool.api.blog.BlogManager
import org.sigmapool.api.calculator.CalcManager
import org.sigmapool.api.chart.ChartManager
import org.sigmapool.api.chart.StubChartManager
import org.sigmapool.api.dashboard.DashboardManager
import org.sigmapool.api.dashboard.StubDashboardManager
import org.sigmapool.api.earnings.EarningsManager
import org.sigmapool.api.earnings.StubEarningsManager
import org.sigmapool.api.login.LoginManager
import org.sigmapool.api.miners.MinerManager
import org.sigmapool.api.news.NewsManager
import org.sigmapool.api.pool.PoolManager
import org.sigmapool.api.pool.StubPoolManager
import org.sigmapool.api.poolinfo.PoolInfoManager
import org.sigmapool.api.workers.StubWorkerManager
import org.sigmapool.api.workers.WorkerManager
import org.sigmapool.common.managers.*

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

    bind<IChartManager>() with singleton { StubChartManager(instance()) }
}