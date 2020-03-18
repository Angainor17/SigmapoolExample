package org.sigmapool.sigmapool.screens.news

import androidx.databinding.ViewDataBinding
import org.sigmapool.common.listLibrary.IItemBindingHelper
import org.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import org.sigmapool.sigmapool.BR
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.screens.news.vm.NewsItemVM

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