package org.sigmapool.sigmapool.screens.earnings.viewModel

import android.os.Handler
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import org.sigmapool.common.managers.IEarningsManager
import org.sigmapool.common.models.AuthDto
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.res.IResProvider
import org.sigmapool.sigmapool.screens.earnings.EarningsBindingHelper
import org.sigmapool.sigmapool.screens.earnings.EarningsItemMapper
import org.sigmapool.sigmapool.screens.earnings.EarningsLoader
import org.sigmapool.sigmapool.screens.earnings.adapter.EarningsListAdapter
import org.sigmapool.sigmapool.screens.earnings.params.EarningsListParams
import org.sigmapool.sigmapool.screens.login.data.AUTH_KEY
import org.sigmapool.sigmapool.screens.settings.viewModel.CoinToolbarVM
import org.sigmapool.sigmapool.utils.interfaces.IUpdateScreenVm
import org.sigmapool.sigmapool.utils.storages.JsonDataStorage
import org.sigmapool.sigmapool.utils.vm.AuthVm

class EarningsVM : AuthVm(), IUpdateScreenVm {

    private val earningsManager by kodein.instance<IEarningsManager>(getManagerMode())
    private val resProvider by kodein.instance<IResProvider>()
    private val jsonDataStorage by kodein.instance<JsonDataStorage>()

    val toolbarVm by kodein.instance<CoinToolbarVM>()
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
        refreshHeader()

        itemsVM.onRefreshListener = this::refreshHeader

        coinProvider.addOnChangeListener {
            params.coin = it

            refreshHeader()
            initListStatus(params.coin)
            itemsVM.onRefresh()
        }

        headerVm.onRefreshListener = this::updateHeader
    }

    private fun initListStatus(coin: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val result = earningsManager.getLastPayment(coin)
            if (result.success) {
                GlobalScope.launch(Dispatchers.Main) {
                    adapter.lastPaymentDate = result.data?.date
                }
            }
        }
    }

    private fun refreshHeader() {
        Handler().postDelayed(
            { headerVm.refresh() },
            500
        )
    }

    private fun updateHeader() {
        GlobalScope.launch(Dispatchers.Main) {
            adapter.notifyItemChanged(0)
        }
    }

    override fun update() {
        val authDto = Gson().fromJson(jsonDataStorage.getJson(AUTH_KEY), AuthDto::class.java)
        toolbarVm.titleLiveData.postValue(authDto?.username ?: resProvider.getString(R.string.demo))
    }
}