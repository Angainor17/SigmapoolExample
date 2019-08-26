package com.sigmapool.app.screens.news.vm

import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import com.sigmapool.common.models.NewsDto
import java.util.*

class NewsItemVM(newsDto: NewsDto) : BaseItemViewModel {

    val title: String = newsDto.title
    val text: String = newsDto.brief
    private val date: Date = newsDto.publishedAt
    val dateString = date.toString()

    companion object {
        val itemType = NewsItemVM::class.hashCode()
    }

    override val itemViewType: Int = itemType

    override fun areItemsTheSame(item: BaseItemViewModel) = this == item

    override fun areContentsTheSame(item: BaseItemViewModel) = item is NewsItemVM && item.title == this.title
}