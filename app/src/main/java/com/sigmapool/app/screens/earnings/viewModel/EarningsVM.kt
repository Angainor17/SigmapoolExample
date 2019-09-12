package com.sigmapool.app.screens.earnings.viewModel

import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.screens.settings.viewModel.CoinToolbarVM
import com.sigmapool.common.managers.IEarningsManager
import org.kodein.di.generic.instance

class EarningsVM : ViewModel() {

    private val earningsManager by kodein.instance<IEarningsManager>()

    val toolbarVm = CoinToolbarVM()

}