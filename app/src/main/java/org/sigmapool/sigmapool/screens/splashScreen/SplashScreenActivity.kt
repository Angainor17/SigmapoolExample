package org.sigmapool.sigmapool.screens.splashScreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.databinding.ActivitySplashBinding
import org.sigmapool.sigmapool.screens.bottomSheetScreen.BottomNavActivity

class SplashScreenActivity : AppCompatActivity(), ISplashScreenVIew {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)

        val binding: ActivitySplashBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_splash
        )
        binding.vm = SplashScreenVm(this)
        binding.lifecycleOwner = this
    }

    override fun startBaseApp() {
        val intent = Intent(this, BottomNavActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {

    }
}