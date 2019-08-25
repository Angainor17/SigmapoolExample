package com.sigmapool.app.kodein

import android.content.Context
import com.sigmapool.app.provider.currency.CurrencyProvider
import com.sigmapool.app.provider.currency.ICurrencyProvider
import com.sigmapool.app.provider.lang.ILanguageProvider
import com.sigmapool.app.provider.lang.LanguageProvider
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.provider.res.ResProvider
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val providersModule = Kodein.Module("ProvidersModule") {

    bind<IResProvider>() with singleton { ResProvider(instance<Context>().resources) }
    bind<ICurrencyProvider>() with singleton { CurrencyProvider() }
    bind<ILanguageProvider>() with singleton { LanguageProvider() }

}
