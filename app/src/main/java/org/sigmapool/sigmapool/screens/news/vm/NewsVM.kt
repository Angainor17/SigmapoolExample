package org.sigmapool.sigmapool.screens.news.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.kodein.di.generic.instance
import org.sigmapool.common.viewModels.ITitleViewModel
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.res.IResProvider
import org.sigmapool.sigmapool.screens.news.INewsFragmentModel
import org.sigmapool.sigmapool.utils.interfaces.IBrowserVm

class NewsVM(val view: INewsFragmentModel) : ViewModel(), ITitleViewModel, IBrowserVm {

    val listVM = NewsListVM()

    private val resProvider by kodein.instance<IResProvider>()

    override fun getTitle() = MutableLiveData(resProvider.getString(R.string.news))
    override val urlLiveData = listVM.urlLiveData

}