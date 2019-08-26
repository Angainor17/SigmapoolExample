package com.sigmapool.app.screens.miningProfit.databinding

import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sigmapool.app.screens.miningProfit.listener.IProfitBtnListener
import com.sigmapool.app.screens.miningProfit.viewModels.MiningProfitListVM
import com.sigmapool.app.screens.news.vm.NewsListVM
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel

@BindingAdapter("setPagedAdapter")
fun setPagedAdapter(view: RecyclerView, listVm: MiningProfitListVM) {
    val linearLayoutManager = LinearLayoutManager(view.context)
    view.layoutManager = linearLayoutManager
    listVm.itemsVM.pagedRecyclerAdapter.linearLayoutManager = linearLayoutManager
    view.adapter = listVm.itemsVM.pagedRecyclerAdapter
    val activity = view.context as AppCompatActivity

    listVm.itemsVM.items.observe(activity,
        Observer<PagedList<BaseItemViewModel>?> { t ->
            listVm.itemsVM.pagedRecyclerAdapter.submitList(t)
        }
    )
}

@BindingAdapter("setNewsAdapter")
fun setNewsAdapter(view: RecyclerView, vm: NewsListVM) {
    val linearLayoutManager = LinearLayoutManager(view.context)
    view.layoutManager = linearLayoutManager
    vm.itemsVM.pagedRecyclerAdapter.linearLayoutManager = linearLayoutManager

    val activity = view.context as AppCompatActivity
    vm.itemsVM.items.observe(activity,
        Observer<PagedList<BaseItemViewModel>?> { t ->
            vm.itemsVM.pagedRecyclerAdapter.submitList(t)
        }
    )
}

@BindingAdapter("app:onProfitClickAction")
fun onClickBasicAction(view: Button, listener: IProfitBtnListener?) {
    val isProfit = false
    view.setOnClickListener {
        it.isActivated = !it.isActivated
        listener?.onProfitBtnSelected(isProfit)
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
