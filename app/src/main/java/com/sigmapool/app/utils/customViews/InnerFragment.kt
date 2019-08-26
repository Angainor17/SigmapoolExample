package com.sigmapool.app.utils.customViews

import androidx.fragment.app.Fragment
import com.sigmapool.app.utils.IBackBtnScreen

abstract class InnerFragment : Fragment(), IBackBtnScreen {

    override fun backBtnClick() {
        activity?.onBackPressed()
    }
}