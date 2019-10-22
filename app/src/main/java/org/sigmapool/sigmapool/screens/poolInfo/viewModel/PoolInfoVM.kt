package org.sigmapool.sigmapool.screens.poolInfo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import org.sigmapool.sigmapool.App
import org.sigmapool.sigmapool.provider.coin.ICoinProvider
import org.sigmapool.sigmapool.screens.bottomSheetScreen.ViewPagerScreen
import org.sigmapool.sigmapool.screens.calculator.viewModel.CoinTabVM
import org.sigmapool.sigmapool.screens.poolInfo.fragments.PoolInfoPageFragment
import org.sigmapool.sigmapool.screens.poolInfo.params.PoolInfoItemParams


class PoolInfoVM : ViewModel() {

    private val coinProvider by App.kodein.instance<ICoinProvider>()

    val tabPositionLiveData = MutableLiveData(ViewPagerScreen(0))
    val coinTabVM = CoinTabVM(tabPositionLiveData)
    val isLoading = MutableLiveData<Boolean>(false)

    private val poolInfoItems: LiveData<List<PoolInfoCoinVM>> = coinProvider.coins.map {
        it.map {
            PoolInfoCoinVM(PoolInfoItemParams(it.text, it.unit))
        }
    }
    val fragments: LiveData<ArrayList<PoolInfoPageFragment>> =
        map(poolInfoItems) {
            onRefresh()
            ArrayList(it.map {
                PoolInfoPageFragment(it)
            }
            )
        }

    init {
        onRefresh()
    }

    fun onRefresh() {
        isLoading.postValue(true)
        GlobalScope.launch(Dispatchers.Default) {
            val deferred = poolInfoItems.value?.map {
                async { it.refreshData() }
            }

            deferred?.forEach { it.await() }

            isLoading.postValue(false)
        }
    }
}