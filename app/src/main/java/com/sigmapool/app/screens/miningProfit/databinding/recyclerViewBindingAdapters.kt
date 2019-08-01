package com.sigmapool.app.screens.miningProfit.databinding

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.sigmapool.app.screens.miningProfit.ItemBindingHelper
import com.sigmapool.common.listLibrary.decorators.SimpleDividerItemDecoration
import com.sigmapool.common.listLibrary.pagedlist.SimplePagedAdapter
import com.sigmapool.common.listLibrary.pagedlist.SimplePagedListViewModel
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel

@BindingAdapter("setPagedAdapter")
fun setPagedAdapter(view: RecyclerView, listVm: SimplePagedListViewModel<BaseItemViewModel, Any>) {
    if (view.adapter == null) {
        val pagedRecyclerAdapter = SimplePagedAdapter(ItemBindingHelper())
        view.adapter = pagedRecyclerAdapter
        val activity = view.context as AppCompatActivity

        listVm.items.observe(activity,
            Observer<PagedList<BaseItemViewModel>?> { t ->
                pagedRecyclerAdapter.submitList(t)
            }
        )
    }
}

@BindingAdapter("addItemDecorator")
fun setDecorator(view: RecyclerView, decoratorId: String) {
    when (decoratorId) {
        "gray_line" -> view.addItemDecoration(
            SimpleDividerItemDecoration(view.context)
        )
        else -> return
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
