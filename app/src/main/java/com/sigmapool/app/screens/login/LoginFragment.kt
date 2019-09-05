package com.sigmapool.app.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sigmapool.app.databinding.FragmentLoginBinding
import com.sigmapool.app.screens.login.viewModel.LoginVM
import com.sigmapool.app.utils.customViews.InnerFragment

class LoginFragment : InnerFragment(), ILoginFragmentModel {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        val vm = LoginVM(this)
        binding.vm = vm
        setUpVm(vm, binding)

        return binding.root
    }
}