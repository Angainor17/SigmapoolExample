package com.sigmapool.api.api

import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

internal val apiModule = Module("ApiModule"){
    bind<IMinerService>() with singleton { StubMinerService() }
}




