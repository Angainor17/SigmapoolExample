package org.sigmapool.sigmapool.utils.customViews.spinner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.Spinner
import org.sigmapool.sigmapool.databinding.CoinSelectorBinding
import org.sigmapool.sigmapool.databinding.CoinSelectorDropdownBinding
import org.sigmapool.sigmapool.provider.coin.ICoinProvider
import org.sigmapool.sigmapool.screens.home.coin.CoinVm

class CustomAdapter(
    private val spinner: Spinner,
    private val coinProvider: ICoinProvider,
    private val coins: ArrayList<CoinVm>
) : BaseAdapter() {

    init {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) = Unit

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = coins[position]
                coinProvider.onCoinSelected(selectedItem)

                coins.remove(selectedItem)
                coins.add(0, selectedItem)
                spinner.setSelection(0)
                notifyDataSetChanged()
            }
        }
    }

    fun refresh() {
        notifyDataSetChanged()
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
        binding.setIcon(coins[i].imageUrl)
        binding.setText(coins[i].text)

        return binding.root
    }

    private fun getCustomViewUnselected(viewGroup: ViewGroup?, i: Int): View {
        val binding = CoinSelectorDropdownBinding.inflate(inflater, viewGroup, false)
        binding.setIcon(coins[i].imageUrl)
        binding.setText(coins[i].text)

        return binding.root
    }
}