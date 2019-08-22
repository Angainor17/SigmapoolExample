package com.sigmapool.app

import android.app.Application
import android.content.Context
import com.sigmapool.api.kodein.managersModule
import com.sigmapool.app.kodein.retrofitModule
import com.sigmapool.app.utils.JsonDataStorage
import com.sigmapool.app.utils.slider.PicassoImageLoadingService
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import ss.com.bannerslider.Slider

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Slider.init(PicassoImageLoadingService(this))
        kodein = Kodein {
            import(retrofitModule)
            import(managersModule)

            bind<Context>() with singleton { this@App }
            bind<JsonDataStorage>() with singleton { JsonDataStorage(instance()) }
        }
    }

    companion object {
        lateinit var kodein: Kodein
    }
}