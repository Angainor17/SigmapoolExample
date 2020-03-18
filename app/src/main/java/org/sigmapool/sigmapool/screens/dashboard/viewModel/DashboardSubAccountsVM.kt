package org.sigmapool.sigmapool.screens.dashboard.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import org.kodein.di.generic.instance
import org.sigmapool.common.models.SubAccountDto
import org.sigmapool.common.utils.trimZeroEnd
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.coin.ICoinProvider
import org.sigmapool.sigmapool.provider.res.IResProvider

class DashboardSubAccountsVM(
    private val coinProvider: ICoinProvider
) : ViewModel() {

    private val res by kodein.instance<IResProvider>()

    val coinLiveData = MutableLiveData(coinProvider.getLabel().toUpperCase())
    val coinValueLiveData = MutableLiveData(0f.trimZeroEnd())
    val hashrateLiveData = MutableLiveData(getHashrate(0f))

    val isDropdownOpen = MutableLiveData(false)

    val listItems = MutableLiveData<ArrayList<SubAccountItemVM>>()
    val hasSubaccounts = map(listItems) { it.isNotEmpty() }

    init {
        initSubAccounts(ArrayList())
    }

    fun initSubAccounts(data: ArrayList<SubAccountDto>) {
        listItems.postValue(
            ArrayList(data.map {
                SubAccountItemVM(
                    it.name,
                    getHashrate(it.hashrate),
                    it.balance.trimZeroEnd(),
                    coinProvider.getLabel().toUpperCase()
                )
            })
        )
        val hashrate = data.sumByDouble { it.hashrate.toDouble() }.toFloat()
        val balance = data.sumByDouble { it.balance.toDouble() }.toFloat()

        hashrateLiveData.postValue(getHashrate(hashrate))
        coinValueLiveData.postValue(balance.trimZeroEnd())

        coinLiveData.postValue(coinProvider.getLabel().toUpperCase())
    }

    fun onHeaderClick() {
        isDropdownOpen.postValue(!(isDropdownOpen.value ?: false))
    }

    private fun getHashrate(hashrate: Float) =
        "" + hashrate.toInt() + " " + res.getString(R.string.hashrate_per_second)

    fun refreshCoin() {
        hashrateLiveData.postValue(getHashrate(0f))
        coinValueLiveData.postValue(0f.trimZeroEnd())
        coinLiveData.postValue(coinProvider.getLabel().toUpperCase())
    }
}