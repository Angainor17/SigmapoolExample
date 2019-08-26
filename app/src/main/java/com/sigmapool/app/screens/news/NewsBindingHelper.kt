package com.sigmapool.app.screens.news

import androidx.databinding.ViewDataBinding
import com.sigmapool.app.BR
import com.sigmapool.app.R
import com.sigmapool.app.screens.news.vm.NewsItemVM
import com.sigmapool.common.listLibrary.IItemBindingHelper
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel

class NewsBindingHelper : IItemBindingHelper {

    override fun getLayoutId(itemType: Int): Int {
        return when (itemType) {
            NewsItemVM.itemType -> R.layout.item_view_news
            else -> throw UnsupportedOperationException()
        }
    }

    override fun getBindingFunction(itemType: Int): (db: ViewDataBinding, vm: BaseItemViewModel) -> Unit {
        return when (itemType) {
            NewsItemVM.itemType -> ::postBinding
            else -> throw UnsupportedOperationException()
        }
    }

    private fun postBinding(db: ViewDataBinding, vm: BaseItemViewModel) {
        if (vm is NewsItemVM) {
            db.setVariable(BR.vm, vm)
            return
        }
    }
}