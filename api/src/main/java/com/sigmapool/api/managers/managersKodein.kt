package com.sigmapool.api.managers

import com.sigmapool.api.api.apiModule
import org.kodein.di.Kodein

internal val managerKodein = Kodein{
    import(apiModule)
}