package org.sigmapool.sigmapool

import android.app.Application
import android.content.Context
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.sigmapool.sigmapool.kodein.apiModule
import org.sigmapool.sigmapool.kodein.providersModule
import org.sigmapool.sigmapool.utils.customViews.slider.PicassoImageLoadingService
import org.sigmapool.sigmapool.utils.storages.JsonDataStorage
import ss.com.bannerslider.Slider

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Slider.init(PicassoImageLoadingService(this))
        kodein = Kodein {
            import(providersModule)
            import(apiModule)

            bind<Context>() with singleton { this@App }
            bind<JsonDataStorage>() with singleton {
                JsonDataStorage(instance())
            }
        }
    }

    companion object {
        lateinit var kodein: Kodein
    }
}