package org.sigmapool.sigmapool.utils.customViews.fragment

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import org.sigmapool.sigmapool.utils.interfaces.IUpdateScreenVm

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