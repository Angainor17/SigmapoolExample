package org.sigmapool.sigmapool.kodein

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import org.sigmapool.api.kodein.managersModule
import org.sigmapool.api.retrofit.HeaderMapper
import org.sigmapool.sigmapool.screens.login.data.AuthHeaderMapper

internal val apiModule = Kodein.Module("ApiModule") {

    bind<ArrayList<HeaderMapper>>() with provider { arrayListOf<HeaderMapper>(AuthHeaderMapper()) }

    import(managersModule)
}