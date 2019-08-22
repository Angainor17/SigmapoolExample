package com.sigmapool.api.kodein

import com.sigmapool.api.blog.BlogManager
import com.sigmapool.api.coin.CoinManager
import com.sigmapool.api.login.LoginManager
import com.sigmapool.api.miners.MinerManager
import com.sigmapool.common.managers.IBlogManager
import com.sigmapool.common.managers.ICoinManager
import com.sigmapool.common.managers.ILoginManager
import com.sigmapool.common.managers.IMinerManager
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val managersModule = Kodein.Module("ManagersModule") {
    import(serviceModule)

    bind<IBlogManager>() with singleton { BlogManager(instance()) }
    bind<ICoinManager>() with singleton { CoinManager(instance()) }
    bind<IMinerManager>() with singleton { MinerManager(instance()) }
    bind<ILoginManager>() with singleton { LoginManager(instance()) }
}