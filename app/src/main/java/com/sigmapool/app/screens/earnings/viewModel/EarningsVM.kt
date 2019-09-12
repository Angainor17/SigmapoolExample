package com.sigmapool.app.screens.earnings.viewModel

import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.screens.earnings.EarningsBindingHelper
import com.sigmapool.app.screens.earnings.EarningsItemMapper
import com.sigmapool.app.screens.earnings.EarningsLoader
import com.sigmapool.app.screens.earnings.adapter.EarningsListAdapter
import com.sigmapool.app.screens.earnings.params.EarningsListParams
import com.sigmapool.app.screens.home.coin.BTC
import com.sigmapool.app.screens.settings.viewModel.CoinToolbarVM
import com.sigmapool.app.utils.interfaces.IUpdateScreenVm
import com.sigmapool.common.managers.IEarningsManager
import org.kodein.di.generic.instance

class EarningsVM : ViewModel(), IUpdateScreenVm {

    private val earningsManager by kodein.instance<IEarningsManager>()

    val toolbarVm = CoinToolbarVM()
    val headerVm = EarningsHeaderVM()
    val adapter = EarningsListAdapter(headerVm, EarningsBindingHelper())
    val params = EarningsListParams(BTC)

    val itemsVM = EarningsListVM(
        EarningsItemMapper(),
        EarningsLoader(params, earningsManager),
        adapter
    )

    override fun update() {

    }
}