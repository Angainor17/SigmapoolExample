package com.sigmapool.app.utils.customViews.spinner

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.Spinner
import com.sigmapool.app.databinding.CoinSelectorBinding
import com.sigmapool.app.databinding.CoinSelectorDropdownBinding
import com.sigmapool.app.screens.home.coin.CoinVm
import com.sigmapool.app.screens.settings.viewModel.CoinToolbarVM

class CustomAdapter(
    private val spinner: Spinner,
    private val vm: CoinToolbarVM,
    private val coins: ArrayList<CoinVm>
) : BaseAdapter() {

    init {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d("", "")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = coins[position]
                vm.coinProvider.onCoinSelected(selectedItem)

                val firstItem = coins[0]
                val lastItem = coins[1]

                coins.clear()
                if (position == 0) {
                    coins.add(firstItem)
                    coins.add(lastItem)
                } else {
                    coins.add(lastItem)
                    coins.add(firstItem)
                }
                spinner.setSelection(0)
                notifyDataSetChanged()
            }
        }
    }

    private var inflater: LayoutInflater = LayoutInflater.from(spinner.context)

    override fun getCount(): Int = coins.size

    override fun getItem(i: Int): Any? = coins[i]

    override fun getItemId(i: Int): Long = i.toLong()

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View {
        return getCustomView(viewGroup, 0)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        if (position == 0) {
            return getCustomView(parent, position)
        }
        return getCustomViewUnselected(parent, position)
    }

    private fun getCustomView(viewGroup: ViewGroup?, i: Int): View {
        val binding = CoinSelectorBinding.inflate(inflater, viewGroup, false)
        binding.setIcon(coins[i].imageResId)
        binding.setText(coins[i].text)

        return binding.root
    }

    private fun getCustomViewUnselected(viewGroup: ViewGroup?, i: Int): View {
        val binding = CoinSelectorDropdownBinding.inflate(inflater, viewGroup, false)
        binding.setIcon(coins[i].imageResId)
        binding.setText(coins[i].text)

        return binding.root
    }
}