package com.sigmapool.app.screens.calculator.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.calculator.ICalculatorFragmentModel
import com.sigmapool.common.viewModels.ITitleViewModel
import org.kodein.di.generic.instance

class CalculatorVM(val view: ICalculatorFragmentModel) : ViewModel(), ITitleViewModel {

    val calculatorTabVM = CalculatorTabVM()

    private val resProvider by kodein.instance<IResProvider>()

    override fun getTitle() = MutableLiveData(resProvider.getString(R.string.calculator))

}