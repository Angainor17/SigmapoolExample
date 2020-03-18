package org.sigmapool.sigmapool.screens.login.viewModel

import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import org.sigmapool.common.managers.ILoginManager
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.res.IResProvider
import org.sigmapool.sigmapool.screens.login.ILoginFragmentModel
import org.sigmapool.sigmapool.screens.login.data.AUTH_KEY
import org.sigmapool.sigmapool.utils.storages.JsonDataStorage
import org.sigmapool.sigmapool.utils.vm.ErrorHandleVm


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
