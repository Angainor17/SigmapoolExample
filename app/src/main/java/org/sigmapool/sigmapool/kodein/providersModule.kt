package org.sigmapool.sigmapool.kodein

import android.content.Context
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.sigmapool.sigmapool.provider.coin.CoinProvider
import org.sigmapool.sigmapool.provider.coin.ICoinProvider
import org.sigmapool.sigmapool.provider.currency.CurrencyProvider
import org.sigmapool.sigmapool.provider.currency.ICurrencyProvider
import org.sigmapool.sigmapool.provider.lang.ILocaleProvider
import org.sigmapool.sigmapool.provider.lang.LocaleProvider
import org.sigmapool.sigmapool.provider.res.IResProvider
import org.sigmapool.sigmapool.provider.res.ResProvider

internal val providersModule = Kodein.Module("ProvidersModule") {

    bind<IResProvider>() with singleton { ResProvider(instance<Context>().resources) }
    bind<ICurrencyProvider>() with singleton { CurrencyProvider() }
    bind<ILocaleProvider>() with singleton { LocaleProvider() }

    bind<ICoinProvider>() with singleton { CoinProvider() }
}
