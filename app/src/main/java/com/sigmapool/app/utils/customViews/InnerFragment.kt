package com.sigmapool.app.utils.customViews

import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.sigmapool.app.utils.interfaces.IBackBtnScreen

abstract class InnerFragment : BaseFragment(), IBackBtnScreen {

    override fun backBtnClick() {
        activity?.onBackPressed()
    }

    fun hideKeyBoard() {
        val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}