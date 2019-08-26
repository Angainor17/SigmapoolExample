package com.sigmapool.app.screens.miningProfit.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.R
import com.sigmapool.app.screens.miningProfit.IMinerFragmentModel
import com.sigmapool.app.utils.getString
import com.sigmapool.common.viewModels.ITitleViewModel

const val COIN_TAG = "coin"
const val GOOGLE_FONT_FAMILY = "Google Sans"//FIXME

class MiningProfitVM(val view: IMinerFragmentModel) : ViewModel(), ITitleViewModel, IMiningProfitToolbarVM {

    val listVM = MiningProfitListVM()

    override fun backBtnClick() {
        view.backBtnClick()
    }

    override fun onProfitBtnSelected(isUpSort: Boolean) {
        //TODO implement sort
    }

    override fun getTitle() = MutableLiveData<String>().apply { value = getString(R.string.mining_profit) }

}
