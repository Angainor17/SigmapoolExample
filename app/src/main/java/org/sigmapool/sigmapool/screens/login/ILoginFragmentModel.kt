package org.sigmapool.sigmapool.screens.login

import org.sigmapool.sigmapool.utils.interfaces.IBackBtnScreen
import org.sigmapool.sigmapool.utils.interfaces.IKeyboardScreen

interface ILoginFragmentModel : IBackBtnScreen, IKeyboardScreen {

    fun setSuccess()

}