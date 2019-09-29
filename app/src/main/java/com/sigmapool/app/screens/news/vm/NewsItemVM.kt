package com.sigmapool.app.screens.news.vm

import com.sigmapool.app.R
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.utils.interfaces.IBrowserVm
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import com.sigmapool.common.models.NewsDto
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

class NewsItemVM(
    private val resProvider: IResProvider,
    newsDto: NewsDto,
    private val browserVm: IBrowserVm
) : BaseItemViewModel {

    val url = newsDto.url
    val title: String = newsDto.title
    val text: String = newsDto.brief
    val date: Date = newsDto.publishedAt
    val dateString = date.formatDate()

    var clickAction = {
        browserVm.urlLiveData.postValue(url)
    }

    companion object {
        val itemType = NewsItemVM::class.hashCode()
    }

    override val itemViewType: Int = itemType
    override fun areItemsTheSame(item: BaseItemViewModel) = this == item
    override fun areContentsTheSame(item: BaseItemViewModel) = false

    private fun Date.formatDate(): String {
        val locale = Locale("ru")
        val dateFormatSymbols = DateFormatSymbols.getInstance(locale)
        val months = resProvider.getStringArray(R.array.months)
        dateFormatSymbols.months = months

        val simpleDateFormat = SimpleDateFormat("d MMMM, HH:mm", locale)
        simpleDateFormat.dateFormatSymbols = dateFormatSymbols
        return simpleDateFormat.format(time)
    }
}

