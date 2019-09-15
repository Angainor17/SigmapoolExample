package com.sigmapool.app.screens.dashboard.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sigmapool.app.databinding.FragmentDashboardSubaccountItemBinding
import com.sigmapool.app.screens.dashboard.viewModel.SubAccountItemVM

class SubAccountsAdapter : RecyclerView.Adapter<SubAccountsAdapter.VH>() {

    private val items = ArrayList<SubAccountItemVM>()

    fun addItems(subAccountItems: ArrayList<SubAccountItemVM>) {
        items.clear()
        items.addAll(subAccountItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = FragmentDashboardSubaccountItemBinding.inflate(
            LayoutInflater.from(parent.context)
        )
        return VH(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position], position)
    }

    class VH(
        private val binding: FragmentDashboardSubaccountItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SubAccountItemVM, position: Int) {
            binding.vm = item
            binding.topSeparatorVisible = position != 0
        }
    }
}