package com.sigmapool.app.screens.login

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.sigmapool.app.databinding.FragmentLoginBinding
import com.sigmapool.app.screens.login.viewModel.LoginViewModel


class LoginFragment : Fragment(), ILoginFragmentModel {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.vm = LoginViewModel(this)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun hideKeyBoard() {
        val inputMethodManager = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}