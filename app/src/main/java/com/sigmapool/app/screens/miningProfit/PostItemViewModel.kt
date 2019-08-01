package com.sigmapool.app.screens.miningProfit

import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import com.sigmapool.common.models.PostDto

class PostItemViewModel(dto: PostDto) : BaseItemViewModel {

    val id = dto.id
    val title = dto.title
    val text = dto.text

    companion object {
        val itemType = PostItemViewModel::class.hashCode()
    }

    override val itemViewType: Int = itemType

    override fun areItemsTheSame(item: BaseItemViewModel): Boolean {
        return this == item
    }

    override fun areContentsTheSame(item: BaseItemViewModel): Boolean {
        return item is PostItemViewModel && item.id == this.id
    }
}

