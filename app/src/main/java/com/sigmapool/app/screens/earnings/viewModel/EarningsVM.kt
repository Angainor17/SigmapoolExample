package com.sigmapool.app.screens.earnings.viewModel

import android.os.Handler
import com.google.gson.Gson
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.earnings.EarningsBindingHelper
import com.sigmapool.app.screens.earnings.EarningsItemMapper
import com.sigmapool.app.screens.earnings.EarningsLoader
import com.sigmapool.app.screens.earnings.adapter.EarningsListAdapter
import com.sigmapool.app.screens.earnings.params.EarningsListParams
import com.sigmapool.app.screens.login.data.AUTH_KEY
import com.sigmapool.app.screens.settings.viewModel.CoinToolbarVM
import com.sigmapool.app.utils.interfaces.IUpdateScreenVm
import com.sigmapool.app.utils.storages.JsonDataStorage
import com.sigmapool.app.utils.vm.AuthVm
import com.sigmapool.common.managers.IEarningsManager
import com.sigmapool.common.models.AuthDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

class EarningsVM : AuthVm(), IUpdateScreenVm {

    private val earningsManager by kodein.instance<IEarningsManager>()
    private val resProvider by kodein.instance<IResProvider>()
    private val jsonDataStorage by kodein.instance<JsonDataStorage>()

    val toolbarVm = CoinToolbarVM()
    private val coinProvider = toolbarVm.coinProvider

    val headerVm = EarningsHeaderVM(coinProvider, earningsManager)
    val adapter = EarningsListAdapter(headerVm, EarningsBindingHelper())
    val params = EarningsListParams(coinProvider.getLabel().toLowerCase())

    val itemsVM = EarningsListVM(
        EarningsItemMapper(coinProvider),
        EarningsLoader(params, earningsManager),
        adapter
    )

    init {
        initListStatus(coinProvider.getLabel())

        coinProvider.addOnChangeListener {
            params.coin = it
            Handler().postDelayed({
                headerVm.refresh()
            }, 500)

            initListStatus(params.coin)
            itemsVM.onRefresh()
        }

        headerVm.onRefreshListener = this::refreshHeader
    }

    private fun initListStatus(coin: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val result = earningsManager.getLastPayment(coin)
            if (result.success) {
                adapter.lastPaymentDate = result.data?.date
            }
        }
    }

    private fun refreshHeader() {
        GlobalScope.launch(Dispatchers.Main) {
            adapter.notifyItemChanged(0)
        }
    }

    override fun update() {
        val authDto = Gson().fromJson(jsonDataStorage.getJson(AUTH_KEY), AuthDto::class.java)
        toolbarVm.titleLiveData.postValue(authDto?.username ?: resProvider.getString(R.string.demo))
    }
}