package com.sigmapool.app.screens.login.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App
import com.sigmapool.app.screens.login.ILoginFragmentModel
import com.sigmapool.app.screens.login.data.AUTH_KEY
import com.sigmapool.app.utils.JsonDataStorage
import com.sigmapool.common.managers.ILoginManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance


class LoginViewModel(private val fragmentModel: ILoginFragmentModel) : ViewModel() {

    private val loginManager: ILoginManager by App.kodein.instance()
    private val jsonDataStorage: JsonDataStorage by App.kodein.instance()

    private val uiScope = CoroutineScope(Dispatchers.Main)

    val errorLiveData = MutableLiveData<String>()

    fun hideKeyBoard(): View.OnClickListener = View.OnClickListener {
        fragmentModel.hideKeyBoard()
        clearEditTextFocus(it)
    }

    fun nexAuth(login: String, password: String) {
        fragmentModel.hideKeyBoard()
        if (login.isNotEmpty() && password.isNotEmpty()) {
            GlobalScope.launch(Dispatchers.Default) {
                val result = loginManager.login(login, password)
                if (result.success) {
                    jsonDataStorage.put(AUTH_KEY, result.data)
                    //TODO navigation
                } else {
                    uiScope.launch {
                        errorLiveData.value = result.error
                    }
                }
            }
        }
    }

    private fun clearEditTextFocus(it: View) {
        it.isFocusableInTouchMode = true
        it.requestFocusFromTouch()
        it.isFocusableInTouchMode = false
    }
}
