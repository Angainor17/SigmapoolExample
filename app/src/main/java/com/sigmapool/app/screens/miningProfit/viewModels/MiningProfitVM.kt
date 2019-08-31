package com.sigmapool.app.screens.miningProfit.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.miningProfit.IMinerFragmentModel
import com.sigmapool.common.viewModels.ITitleViewModel
import org.kodein.di.generic.instance

const val COIN_TAG = "coin"

class MiningProfitVM(val view: IMinerFragmentModel) : ViewModel(), ITitleViewModel, IMiningProfitToolbarVM {

    private val res by kodein.instance<IResProvider>()

    val listVM = MiningProfitListVM()

    override fun backBtnClick() {
        view.backBtnClick()
    }

    override fun onProfitBtnSelected(isUpSort: Boolean) {
        //TODO implement sort
    }

    override fun getTitle() = MutableLiveData(res.getString(R.string.mining_profit))

}
