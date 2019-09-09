package com.sigmapool.app.screens.settings

import androidx.fragment.app.FragmentManager

interface ISettingsView {

    fun sendEmail(email: String)

    fun markApp()

    fun recreate()

    fun fragmentManager(): FragmentManager
}