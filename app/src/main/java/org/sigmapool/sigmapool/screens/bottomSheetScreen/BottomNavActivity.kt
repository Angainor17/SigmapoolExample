package org.sigmapool.sigmapool.screens.bottomSheetScreen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.databinding.ActivityBottomNavBinding
import org.sigmapool.sigmapool.screens.bottomSheetScreen.viewModel.BottomNavVM
import org.sigmapool.sigmapool.utils.customViews.ColoredToolbarActivity
import org.sigmapool.sigmapool.utils.eventBus.LogoutEvent

const val SCREEN_KEY = "screen"

const val SETTINGS_ITEM_ID = R.id.settings
const val SETTINGS_POS = 4

class BottomNavActivity : ColoredToolbarActivity(), IBottomSheetScreen {

    val vm = BottomNavVM(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)
        val binding: ActivityBottomNavBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_bottom_nav)

        binding.lifecycleOwner = this
        binding.vm = vm
        binding.fragmentManager = supportFragmentManager

        val screenPos = intent?.getIntExtra(SCREEN_KEY, -1)
        if (screenPos ?: 0 == SETTINGS_POS) {
            binding.bottomNavigation.selectedItemId = SETTINGS_ITEM_ID
            vm.screenPositionLiveData.postValue(ViewPagerScreen(SETTINGS_POS, false))
        }
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
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

    override fun recreate() {
        finish()
        startActivity(intent)
    }
}