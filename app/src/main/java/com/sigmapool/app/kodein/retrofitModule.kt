package com.sigmapool.app.kodein

import com.sigmapool.api.kodein.managersModule
import com.sigmapool.api.retrofit.HeaderMapper
import com.sigmapool.app.screens.login.data.AuthHeaderMapper
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

internal val apiModule = Kodein.Module("ApiModule") {

    bind<ArrayList<HeaderMapper>>() with provider { arrayListOf<HeaderMapper>(AuthHeaderMapper()) }

    import(managersModule)
}