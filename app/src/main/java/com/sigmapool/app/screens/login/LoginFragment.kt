package com.sigmapool.app.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.lifecycle.Observer
import com.sigmapool.app.databinding.FragmentLoginBinding
import com.sigmapool.app.screens.login.viewModel.LoginVM
import com.sigmapool.app.utils.customViews.InnerFragment

class LoginFragment : InnerFragment(), ILoginFragmentModel {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        val vm = LoginVM(this)
        binding.vm = vm
        binding.lifecycleOwner = this
        vm.errorLiveData.observe(this, Observer {
            toast(it)
        })
        return binding.root
    }

    private fun toast(text: String) {
        makeText(context, text, LENGTH_SHORT).show()
    }
}