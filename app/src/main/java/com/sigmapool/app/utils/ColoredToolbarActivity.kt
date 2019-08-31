package com.sigmapool.app.utils

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.sigmapool.app.R

abstract class ColoredToolbarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarGradient()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun setStatusBarGradient() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = this.window
            val background = this.resources.getDrawable(R.drawable.gradient_status_bar)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = this.resources.getColor(android.R.color.transparent)
            window.navigationBarColor = this.resources.getColor(android.R.color.black)
            window.setBackgroundDrawable(background)
        }
    }
}