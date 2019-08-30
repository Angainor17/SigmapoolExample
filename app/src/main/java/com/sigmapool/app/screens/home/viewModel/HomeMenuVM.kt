package com.sigmapool.app.screens.home.viewModel

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.R
import com.sigmapool.app.screens.calculator.CalculatorFragment
import com.sigmapool.app.screens.miningProfit.MiningProfitFragment
import com.sigmapool.app.screens.news.NewsFragment


class HomeMenuVM(private val fragmentLiveData: MutableLiveData<Class<out Fragment>>) : ViewModel() {

    val poolInfoVM = HomeMenuItem(R.mipmap.ic_info, R.string.pool_info, View.OnClickListener {
        //TODO implement
    })

    val calculatorVM = HomeMenuItem(R.mipmap.ic_calc, R.string.calculator, View.OnClickListener {
        fragmentLiveData.postValue(CalculatorFragment::class.java)
    })

    val miningProfitVM = HomeMenuItem(R.mipmap.ic_mining_profit, R.string.mining_profit, View.OnClickListener {
        fragmentLiveData.postValue(MiningProfitFragment::class.java)
    })

    val newsVM = HomeMenuItem(R.mipmap.ic_rect, R.string.news, View.OnClickListener {
        fragmentLiveData.postValue(NewsFragment::class.java)
    })
}