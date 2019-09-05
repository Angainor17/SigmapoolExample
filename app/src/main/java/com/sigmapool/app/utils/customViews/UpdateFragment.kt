package com.sigmapool.app.utils.customViews

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.sigmapool.app.utils.interfaces.IUpdateScreenVm

abstract class UpdateFragment : BaseFragment() {

    var updateVm: IUpdateScreenVm? = null

    override fun onStart() {
        super.onStart()
        updateVm?.update()
    }

    override fun setUpVm(vm: ViewModel, binding: ViewDataBinding) {
        super.setUpVm(vm, binding)
        if (vm is IUpdateScreenVm) {
            updateVm = vm
        }
    }
}