package com.sigmapool.app.screens.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sigmapool.app.R
import com.sigmapool.app.databinding.FragmentSettingsBinding
import com.sigmapool.app.screens.settings.viewModel.SettingsVM

class SettingsFragment : Fragment(), ISettingsView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val vm = SettingsVM(this)
        binding.vm = vm

        binding.lifecycleOwner = this
        return binding.root
    }

    override fun sendEmail(email: String) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.sigmapool_android_app))

        startActivity(Intent.createChooser(intent, getString(R.string.email_via)))
    }

    override fun markApp() {
        val appPackageName = activity!!.packageName
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
        } catch (e: Exception) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                )
            )
        }
    }
}