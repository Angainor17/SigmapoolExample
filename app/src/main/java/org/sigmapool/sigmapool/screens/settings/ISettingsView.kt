package org.sigmapool.sigmapool.screens.settings

import android.content.Context
import androidx.fragment.app.FragmentManager

interface ISettingsView {

    fun sendEmail(email: String)

    fun markApp()

    fun recreate()

    fun fragmentManager(): FragmentManager

    fun context(): Context
}