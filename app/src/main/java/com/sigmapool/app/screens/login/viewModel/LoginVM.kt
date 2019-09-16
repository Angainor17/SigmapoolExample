package com.sigmapool.app.screens.login.viewModel

import android.view.View
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.login.ILoginFragmentModel
import com.sigmapool.app.screens.login.data.AUTH_KEY
import com.sigmapool.app.utils.storages.JsonDataStorage
import com.sigmapool.app.utils.vm.ErrorHandleVm
import com.sigmapool.common.managers.ILoginManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance


class LoginVM(private val view: ILoginFragmentModel) : ErrorHandleVm() {

    private val loginManager: ILoginManager by kodein.instance()
    private val jsonDataStorage: JsonDataStorage by kodein.instance()
    private val res by kodein.instance<IResProvider>()

    private val uiScope = CoroutineScope(Dispatchers.Main)

    fun hideKeyBoard(): View.OnClickListener = View.OnClickListener {
        view.hideKeyBoard()
        clearEditTextFocus(it)
    }

    fun doAuth(login: String, password: String) {
        view.hideKeyBoard()
        if (login.isNotEmpty() && password.isNotEmpty()) {
            GlobalScope.launch(Dispatchers.Default) {
                val result = loginManager.login(login, password)
                if (result.success) {
                    jsonDataStorage.put(AUTH_KEY, result.data)
                    view.setSuccess()
                    exit()
                } else {
                    uiScope.launch {
                        if ((result.error ?: "").contains("400")) {
                            errorLiveData.value = res.getString(R.string.wrong_sign_in_data)
                            return@launch
                        }
                        errorLiveData.value = result.error
                    }
                }
            }
        }
    }

    private fun exit() {
        GlobalScope.launch(Dispatchers.Main) {
            view.backBtnClick()
        }
    }

    private fun clearEditTextFocus(it: View) {
        it.isFocusableInTouchMode = true
        it.requestFocusFromTouch()
        it.isFocusableInTouchMode = false
    }
}
