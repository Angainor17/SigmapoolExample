package com.sigmapool.app.screens.bottomSheetScreen

import android.os.Bundle
import com.sigmapool.app.R
import com.sigmapool.app.utils.ColoredToolbarActivity

class BottomNavActivity : ColoredToolbarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

//        supportFragmentManager.beginTransaction()//TODO implement navigation
//            .replace(R.id.container, HomeFragment())
////            .replace(R.id.container, LoginFragment())
//            .commitNow()
    }
}