package com.sigmapool.app.screens.news.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.news.INewsFragmentModel
import com.sigmapool.common.viewModels.ITitleViewModel
import org.kodein.di.generic.instance

class NewsVM(val view: INewsFragmentModel) : ViewModel(), ITitleViewModel {

    val listVM = NewsListVM()

    private val resProvider by kodein.instance<IResProvider>()

    override fun getTitle(): LiveData<String> = MutableLiveData(resProvider.getString(R.string.news))
}