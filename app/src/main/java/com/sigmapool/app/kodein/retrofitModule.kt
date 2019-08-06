package com.sigmapool.app.kodein

import com.sigmapool.api.kodein.managersModule
import com.sigmapool.api.retrofit.HeaderMapper
import com.sigmapool.app.screens.login.data.AuthHeaderMapper
import com.sigmapool.app.utils.JsonDataStorage
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

//TODO rename
internal val retrofitModule = Kodein.Module("RetrofitModule") {

    bind<HeaderMapper>() with provider { AuthHeaderMapper() }
    bind<JsonDataStorage>() with singleton { JsonDataStorage(instance()) }

    import(managersModule)
}
