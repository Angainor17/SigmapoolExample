package com.sigmapool.app.utils.customViews

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.res.IResProvider
import org.kodein.di.generic.instance

abstract class ColoredToolbarActivity : AppCompatActivity() {

    private val res by kodein.instance<IResProvider>()

    override fun onCreate(savedInstanceState: Bundle?) {
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