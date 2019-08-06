package com.sigmapool.app

import android.app.Application
import android.content.Context
import com.sigmapool.app.kodein.retrofitModule
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        kodein = Kodein {
            bind<Context>() with singleton { this@App }
            import(retrofitModule)
        }
    }

    companion object {
        lateinit var kodein: Kodein
    }
}