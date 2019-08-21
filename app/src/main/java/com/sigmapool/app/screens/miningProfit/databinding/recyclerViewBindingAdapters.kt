package com.sigmapool.app.screens.miningProfit.databinding

import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.sigmapool.app.screens.miningProfit.viewModels.IMiningProfitToolbarViewModel
import com.sigmapool.app.screens.miningProfit.viewModels.MiningProfitViewModel
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel

@BindingAdapter("setPagedAdapter")
fun setPagedAdapter(view: RecyclerView, listVm: MiningProfitViewModel) {
    if (view.adapter == null) {
        view.adapter = listVm.itemsVM.pagedRecyclerAdapter
        val activity = view.context as AppCompatActivity

        listVm.itemsVM.items.observe(activity,
            Observer<PagedList<BaseItemViewModel>?> { t ->
                listVm.itemsVM.pagedRecyclerAdapter.submitList(t)
            }
        )
    }
}

@BindingAdapter("app:onProfitClickAction")
fun onClickBasicAction(view: Button, toolbarVm: IMiningProfitToolbarViewModel?) {
    val isProfit = false
    view.setOnClickListener {
        it.isActivated = !it.isActivated
        toolbarVm?.onProfitBtnSelected(isProfit)
    }
}

@BindingAdapter("goneIfBlank")
fun goneIfValueBlank(view: View, value: String?) {
    view.visibility = if (value.isNullOrEmpty()) View.GONE else View.VISIBLE
}

@BindingAdapter("goneIfNotBlank")
fun goneIfValueNotBlank(view: View, value: String?) {
    view.visibility = if (value.isNullOrEmpty()) View.VISIBLE else View.GONE
}
