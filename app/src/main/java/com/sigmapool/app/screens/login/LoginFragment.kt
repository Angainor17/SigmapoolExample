package com.sigmapool.app.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sigmapool.app.databinding.FragmentLoginBinding
import com.sigmapool.app.screens.login.viewModel.LoginVM
import com.sigmapool.app.utils.customViews.fragment.InnerFragment

class LoginFragment : InnerFragment(), ILoginFragmentModel {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        val vm = LoginVM(this)
        binding.vm = vm
        binding.toolbarVm = this
        setUpVm(vm, binding)

        return binding.root
    }
}