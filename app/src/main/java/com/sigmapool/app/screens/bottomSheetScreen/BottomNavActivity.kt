package com.sigmapool.app.screens.bottomSheetScreen

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sigmapool.app.R
import com.sigmapool.app.databinding.ActivityBottomNavBinding
import com.sigmapool.app.screens.bottomSheetScreen.viewModel.BottomNavVM
import com.sigmapool.app.utils.ColoredToolbarActivity

class BottomNavActivity : ColoredToolbarActivity(), IBottomSheetScreen {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityBottomNavBinding = DataBindingUtil.setContentView(this, R.layout.activity_bottom_nav)
        val vm = BottomNavVM(this)

        binding.lifecycleOwner = this
        binding.vm = vm

//        supportFragmentManager.beginTransaction()//TODO implement navigation
//            .replace(R.id.container, HomeFragment())
////            .replace(R.id.container, LoginFragment())
//            .commitNow()
    }

    override fun navigate() {

    }
}