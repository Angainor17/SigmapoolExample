package org.sigmapool.sigmapool.screens.workers

import org.sigmapool.common.listLibrary.datasource.SimpleMapper
import org.sigmapool.common.models.WorkerDto
import org.sigmapool.sigmapool.provider.res.IResProvider
import org.sigmapool.sigmapool.screens.workers.viewModel.WorkersListItemVM

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