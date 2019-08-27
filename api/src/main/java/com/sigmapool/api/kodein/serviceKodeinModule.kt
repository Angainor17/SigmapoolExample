package com.sigmapool.api.kodein

import com.sigmapool.api.miners.IMinerService
import com.sigmapool.api.miners.StubMinerService
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

internal val serviceModule = Module("ServiceModule") {

    bind<IMinerService>() with singleton { StubMinerService() }

}
