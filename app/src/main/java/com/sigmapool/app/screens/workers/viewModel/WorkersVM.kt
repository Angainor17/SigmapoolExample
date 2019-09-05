package com.sigmapool.app.screens.workers.viewModel

import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.screens.settings.viewModel.CoinToolbarVM
import com.sigmapool.common.managers.IWorkersManager
import org.kodein.di.generic.instance

class WorkersVM : ViewModel() {

    private val workerManager: IWorkersManager by kodein.instance()

    val toolbarVm = CoinToolbarVM()
    val tabVm = WorkersTabVM()

}