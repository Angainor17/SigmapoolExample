package com.sigmapool.app.screens.login

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.lifecycle.Observer
import com.sigmapool.app.databinding.FragmentLoginBinding
import com.sigmapool.app.screens.login.viewModel.LoginViewModel
import com.sigmapool.app.utils.customViews.InnerFragment


class LoginFragment : InnerFragment(), ILoginFragmentModel {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        val vm = LoginViewModel(this)
        binding.vm = vm
        binding.lifecycleOwner = this
        vm.errorLiveData.observe(this, Observer {
            toast(it)
        })
        return binding.root
    }

    override fun hideKeyBoard() {
        val inputMethodManager = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun toast(text: String) {
        makeText(context, text, LENGTH_SHORT).show()
    }
}