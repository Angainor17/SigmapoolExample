package com.sigmapool.app.utils.customViews.fragment

import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.sigmapool.app.utils.vm.ErrorHandleVm

abstract class BaseFragment : Fragment() {

    open fun setUpVm(vm: ViewModel, binding: ViewDataBinding) {
        binding.lifecycleOwner = this

        if (vm is ErrorHandleVm) {
            vm.errorLiveData.observe(this, Observer {
                toast(it)
            })
        }
    }

    private fun toast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}