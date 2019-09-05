package com.sigmapool.app.screens.miningProfit.databinding

import android.os.Build
import android.text.Html
import android.text.util.Linkify
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sigmapool.app.screens.miningProfit.MiningListAdapter
import com.sigmapool.app.screens.miningProfit.listener.IProfitBtnListener
import com.sigmapool.app.screens.miningProfit.viewModels.MiningProfitListVM
import com.sigmapool.app.screens.news.vm.NewsListVM
import com.sigmapool.app.screens.workers.viewModel.WorkersListVM
import com.sigmapool.common.listLibrary.PaginationListener
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel

@BindingAdapter("setPagedAdapter")
fun setPagedAdapter(view: RecyclerView, vm: MiningProfitListVM) {
    val linearLayoutManager = LinearLayoutManager(view.context)
    view.layoutManager = linearLayoutManager

    val itemsVM = vm.itemsVM
    view.adapter = itemsVM.adapter

    view.addOnScrollListener(object : PaginationListener(linearLayoutManager) {
        override fun loadMoreItems() {
            itemsVM.loadMoreItems()
        }

        override fun isLastPage(): Boolean = itemsVM.isLastPage.value ?: false
        override fun isLoading(): Boolean = itemsVM.isLoading.value ?: true
        override fun headerCount(): Int = 1
    })

    val adapter = itemsVM.adapter as MiningListAdapter
    itemsVM.items.observe(view.context as AppCompatActivity, Observer {
        adapter.addItems(it)
        adapter.notifyItemRangeChanged(1, adapter.items.size)
    })
}

@BindingAdapter("setNewsAdapter")
fun setNewsAdapter(view: RecyclerView, vm: NewsListVM) {
    view.adapter = vm.itemsVM.pagedRecyclerAdapter

    val activity = view.context as AppCompatActivity
    vm.itemsVM.items.observe(activity,
        Observer<PagedList<BaseItemViewModel>?> { t ->
            vm.itemsVM.pagedRecyclerAdapter.submitList(t)
        }
    )
}

@BindingAdapter("setWorkersAdapter")
fun setWorkersAdapter(view: RecyclerView, vm: WorkersListVM) {
    view.adapter = vm.itemsVM.pagedRecyclerAdapter

    val activity = view.context as AppCompatActivity
    vm.itemsVM.items.observe(activity,
        Observer<PagedList<BaseItemViewModel>?> { t ->
            vm.itemsVM.pagedRecyclerAdapter.submitList(t)
        }
    )
}

@BindingAdapter("app:onProfitClickAction")
fun onClickBasicAction(view: View, listener: IProfitBtnListener?) {
    var isProfit = false
    view.setOnClickListener {
        it.isActivated = !it.isActivated
        isProfit = !isProfit
        listener?.onProfitBtnSelected(isProfit)
    }
}

@BindingAdapter("app:onClickAction", "app:disabledView", requireAll = false)
fun onClickBasicAction(view: View, listener: View.OnClickListener?, disabledView: List<View?>?) {
    view.setOnClickListener {
        if (!it.isActivated) {
            it.isActivated = !it.isActivated
            listener?.onClick(view)

            disabledView?.forEach { it ->
                it?.isActivated = false
            }
        }
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

@BindingAdapter("htmlText")
fun htmlText(view: TextView, value: String?) {
    Linkify.addLinks(view, Linkify.WEB_URLS)

    view.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(value, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(value)
    }
}
