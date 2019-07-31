package com.sigmapool.app

import android.app.Application
import android.content.Context
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        kodein = Kodein {
            bind<Context>() with provider { this@App }
        }
    }

    companion object {
        lateinit var kodein: Kodein
    }
}