package com.sigmapool.app.screens.bottomSheetScreen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sigmapool.app.R
import com.sigmapool.app.databinding.ActivityBottomNavBinding
import com.sigmapool.app.screens.bottomSheetScreen.viewModel.BottomNavVM
import com.sigmapool.app.utils.customViews.ColoredToolbarActivity
import com.sigmapool.app.utils.eventBus.LogoutEvent
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class BottomNavActivity : ColoredToolbarActivity(), IBottomSheetScreen {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)
        val binding: ActivityBottomNavBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_bottom_nav)
        val vm = BottomNavVM(this)

        binding.lifecycleOwner = this
        binding.vm = vm
        binding.fragmentManager = supportFragmentManager
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun logoutEvent(event: LogoutEvent) {
        recreate()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            recreate()
        }
    }
}