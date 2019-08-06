package com.sigmapool.app.screens.login.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App
import com.sigmapool.app.screens.login.ILoginFragmentModel
import com.sigmapool.app.utils.JsonDataStorage
import com.sigmapool.common.managers.ILoginManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance


class LoginViewModel(private val fragmentModel: ILoginFragmentModel) : ViewModel() {

    private val loginManager: ILoginManager by App.kodein.instance()
    private val jsonDataStorage: JsonDataStorage by App.kodein.instance()

    val errorLiveData = MutableLiveData<String>()

    fun hideKeyBoard(): View.OnClickListener = View.OnClickListener {
        fragmentModel.hideKeyBoard()
        it.requestFocus()
    }

    fun nexAuth(login: String, password: String) {
        if (login.isNotEmpty() && password.isNotEmpty()) {
            GlobalScope.launch(Dispatchers.Default) {
                val result = loginManager.login("sigma171717888@gmail.com", "MinerPa$$88")
                if (result.success) {
                    jsonDataStorage.put("", result.data)
                    //TODO navigation
                } else {
                    errorLiveData.postValue(result.error)
                }
            }
        }
    }
}
