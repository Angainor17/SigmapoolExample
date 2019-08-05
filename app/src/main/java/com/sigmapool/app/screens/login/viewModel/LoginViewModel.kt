package com.sigmapool.app.screens.login.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.sigmapool.app.screens.login.ILoginFragmentModel


class LoginViewModel(private val fragmentModel: ILoginFragmentModel) : ViewModel() {

    fun hideKeyBoard(): View.OnClickListener = View.OnClickListener {
        fragmentModel.hideKeyBoard()
        it.requestFocus()
    }

    fun nexAuth(login: String, password: String) {
        Log.d("", "")
    }
}
