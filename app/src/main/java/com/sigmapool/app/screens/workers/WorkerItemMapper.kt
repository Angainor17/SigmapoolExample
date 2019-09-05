package com.sigmapool.app.screens.workers

import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.workers.viewModel.WorkersListItemVM
import com.sigmapool.common.listLibrary.datasource.SimpleMapper
import com.sigmapool.common.models.WorkerDto

class WorkerItemMapper(private val resProvider: IResProvider) : SimpleMapper<WorkerDto, WorkersListItemVM>() {

    override fun map(input: WorkerDto): WorkersListItemVM {
        return WorkersListItemVM(
            resProvider,
            input.hashrate,
            input.hashrate24h,
            input.title,
            input.isOnline
        )
    }
}