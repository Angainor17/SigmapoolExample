package com.sigmapool.app.utils

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity


abstract class ColoredToolbarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}