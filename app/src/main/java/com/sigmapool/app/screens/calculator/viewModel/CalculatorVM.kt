package com.sigmapool.app.screens.calculator.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App
import com.sigmapool.app.R
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.calculator.ICalculatorFragmentModel
import com.sigmapool.common.viewModels.ITitleViewModel
import org.kodein.di.generic.instance

class CalculatorVM(view: ICalculatorFragmentModel) : ViewModel(), ITitleViewModel {

    private val resProvider by App.kodein.instance<IResProvider>()

    override fun getTitle(): LiveData<String> = MutableLiveData(resProvider.getString(R.string.calculator))

}