package org.sigmapool.sigmapool.utils.customViews

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.generic.instance
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.lang.ILocaleProvider
import org.sigmapool.sigmapool.provider.res.IResProvider

abstract class ColoredToolbarActivity : AppCompatActivity() {

    private val res by kodein.instance<IResProvider>()
    private val langProvider by kodein.instance<ILocaleProvider>()

    override fun onCreate(savedInstanceState: Bundle?) {
        langProvider.setUpLocale(this)

        super.onCreate(savedInstanceState)
        setStatusBarGradient()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun setStatusBarGradient() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = this.window
            val background = res.getDrawable(R.drawable.gradient_status_bar)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = res.getColor(android.R.color.transparent)
            window.navigationBarColor = res.getColor(android.R.color.black)
            window.setBackgroundDrawable(background)
        }
    }
}