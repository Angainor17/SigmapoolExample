package org.sigmapool.sigmapool.screens.login

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sigmapool.sigmapool.databinding.FragmentLoginBinding
import org.sigmapool.sigmapool.screens.login.viewModel.LoginVM
import org.sigmapool.sigmapool.utils.customViews.fragment.InnerFragment

class LoginFragment : InnerFragment(), ILoginFragmentModel {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        val vm = LoginVM(this)
        binding.vm = vm
        binding.toolbarVm = this
        setUpVm(vm, binding)

        return binding.root
    }

    override fun setSuccess() {
        activity?.setResult(RESULT_OK)
    }
}