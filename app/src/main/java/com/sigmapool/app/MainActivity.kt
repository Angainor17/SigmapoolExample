package com.sigmapool.app

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.sigmapool.app.screens.login.LoginFragment
import com.sigmapool.common.managers.ILoginManager
import com.sigmapool.common.managers.IPoolInfoManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity() {

    private val poolInfoManager: IPoolInfoManager by App.kodein.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContentView(R.layout.activity_main)

        test()

        supportFragmentManager.beginTransaction()//TODO implement navigation
            .replace(R.id.container, LoginFragment())
            .commitNow()
    }

    private fun test(){
        GlobalScope.launch(Dispatchers.Default) {
            val info = poolInfoManager.getPoolInfo()
            val b = 1;
        }
    }
}