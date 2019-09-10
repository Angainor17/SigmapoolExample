package com.sigmapool.app.screens.news.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.news.INewsFragmentModel
import com.sigmapool.app.utils.interfaces.IBrowserVm
import com.sigmapool.common.viewModels.ITitleViewModel
import org.kodein.di.generic.instance

class NewsVM(val view: INewsFragmentModel) : ViewModel(), ITitleViewModel, IBrowserVm {

    val listVM = NewsListVM()

    private val resProvider by kodein.instance<IResProvider>()

    override fun getTitle() = MutableLiveData(resProvider.getString(R.string.news))
    override val urlLiveData = listVM.urlLiveData

}