package org.sigmapool.sigmapool.utils.customViews.viewPager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import org.sigmapool.sigmapool.BR
import org.sigmapool.sigmapool.databinding.CoinSelectorTabSelectedBinding
import org.sigmapool.sigmapool.databinding.CoinSelectorTabUnselectedBinding
import org.sigmapool.sigmapool.provider.coin.ICoinProvider
import org.sigmapool.sigmapool.screens.bottomSheetScreen.ViewPagerScreen
import org.sigmapool.sigmapool.screens.calculator.viewModel.CalcTabItemVM

private const val SELECTED_ITEM = 0
private const val UN_SELECTED_ITEM = 1

class CoinTabAdapter(
    val coinProvider: ICoinProvider,
    val tabPositionLiveData: MutableLiveData<ViewPagerScreen>
) : RecyclerView.Adapter<CoinTabAdapter.VH>() {

    var selectedItemPosition = 0
        set(value) {
            field = value
            notifyDataSetChanged()
            tabPositionLiveData.postValue(ViewPagerScreen(value))
        }
    var coins = ArrayList<CalcTabItemVM>()

    init {
        coinProvider.coins.observeForever {
            coins.clear()
            coins.addAll(
                it.map {
                    CalcTabItemVM(it.text, MutableLiveData(it.imageUrl))
                }
            )

            if (coins.size > 0) {
                coins[0].activatedLiveData.postValue(true)
            }

            notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == selectedItemPosition) SELECTED_ITEM else UN_SELECTED_ITEM
    }

    override fun getItemCount(): Int = coins.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layoutInflater = LayoutInflater.from(parent.context)
        if (viewType == SELECTED_ITEM) {
            return VH(CoinSelectorTabSelectedBinding.inflate(layoutInflater))
        }
        return VH(CoinSelectorTabUnselectedBinding.inflate(layoutInflater))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = coins[position]
        holder.setData(item, position)
    }

    inner class VH(private val dataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {

        fun setData(vm: CalcTabItemVM, position: Int) {
            dataBinding.setVariable(BR.icon, vm.iconUrl.value)
            dataBinding.setVariable(BR.text, vm.text)

            dataBinding.root.setOnClickListener {
                if (selectedItemPosition != position) {
                    selectedItemPosition = position
                }
            }
        }
    }
}