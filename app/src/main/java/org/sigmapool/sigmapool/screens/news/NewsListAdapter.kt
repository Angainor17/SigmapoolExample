package org.sigmapool.sigmapool.screens.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import org.sigmapool.common.listLibrary.IItemBindingHelper
import org.sigmapool.common.listLibrary.pagedlist.SimpleAdapter
import org.sigmapool.sigmapool.screens.news.params.NewsListParams
import org.sigmapool.sigmapool.screens.news.vm.NewsItemVM

class NewsListAdapter(
    itemLayoutProvider: IItemBindingHelper,
    val params: NewsListParams
) :
    SimpleAdapter<NewsItemVM>(itemLayoutProvider) {

    override fun addItems(newItems: List<NewsItemVM>) {
        if (params.displayedSize != params.pageSize) {
            items.clear()
            items.addAll(
                newItems.sortedByDescending { it.date.time }.take(params.displayedSize)
            )
        } else {
            items.addAll(newItems)
            items.sortByDescending { it.date.time }
        }

        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = NewsItemVM.itemType

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemLayoutProvider.getBindingFunction(viewType),
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                itemLayoutProvider.getLayoutId(viewType),
                parent,
                false
            )
        )
    }
}