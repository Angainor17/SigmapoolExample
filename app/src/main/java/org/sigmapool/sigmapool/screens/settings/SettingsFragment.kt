package org.sigmapool.sigmapool.screens.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.commit451.modalbottomsheetdialogfragment.ModalBottomSheetDialogFragment
import com.commit451.modalbottomsheetdialogfragment.Option
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.databinding.FragmentSettingsBinding
import org.sigmapool.sigmapool.screens.bottomSheetScreen.SCREEN_KEY
import org.sigmapool.sigmapool.screens.bottomSheetScreen.SETTINGS_POS
import org.sigmapool.sigmapool.screens.settings.viewModel.SettingsVM
import org.sigmapool.sigmapool.utils.customViews.fragment.UpdateFragment

class SettingsFragment : UpdateFragment(), ISettingsView, ModalBottomSheetDialogFragment.Listener {

    val vm = SettingsVM(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.vm = vm
        setUpVm(vm, binding)

        return binding.root
    }

    override fun recreate() {
        GlobalScope.launch(Dispatchers.Main) {
            activity?.intent?.putExtra(SCREEN_KEY, SETTINGS_POS)
            activity?.recreate()
        }
    }

    override fun context(): Context = context!!

    override fun sendEmail(email: String) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.sigmapool_android_app))

        startActivity(Intent.createChooser(intent, getString(R.string.email_via)))
    }

    override fun onModalOptionSelected(tag: String?, option: Option) {
        vm.onModalOptionSelected(tag, option)
    }

    override fun fragmentManager(): FragmentManager = childFragmentManager

    override fun markApp() {
        val appPackageName = activity!!.packageName
        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$appPackageName")
                )
            )
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