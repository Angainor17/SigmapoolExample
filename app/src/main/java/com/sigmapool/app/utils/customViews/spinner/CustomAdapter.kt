package com.sigmapool.app.utils.customViews.spinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.sigmapool.app.databinding.CoinSelectorBinding
import com.sigmapool.app.screens.home.coin.CoinVm


class CustomAdapter(var context: Context, var coins: ArrayList<CoinVm>) : BaseAdapter() {

    private var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int = coins.size

    override fun getItem(i: Int): Any? = coins[i]

    override fun getItemId(i: Int): Long = i.toLong()


    override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View {
        return getCustomView(viewGroup, i)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return getCustomView(parent, position)
    }

    private fun getCustomView(viewGroup: ViewGroup?, i: Int): View {
        var binding = CoinSelectorBinding.inflate(inflater, viewGroup, false)
        binding.setIcon(coins[i].imageResId)
        binding.setText(coins[i].text)

        return binding.root
    }
}