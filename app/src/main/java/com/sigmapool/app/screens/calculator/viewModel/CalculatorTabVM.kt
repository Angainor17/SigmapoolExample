package com.sigmapool.app.screens.calculator.viewModel

import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.utils.customViews.AbstractCustomTabView
import org.kodein.di.generic.instance

class CalculatorTabVM : AbstractCustomTabView() {

    private val resProvider by kodein.instance<IResProvider>()

    override val leftTabText: String = resProvider.getString(R.string.bitcoin)
    override val rightTabText: String = resProvider.getString(R.string.litecoin)

    override fun leftClickAction() {
        super.leftClickAction()
        //TODO
    }

    override fun rightClickAction() {
        super.rightClickAction()
        //TODO
    }
}