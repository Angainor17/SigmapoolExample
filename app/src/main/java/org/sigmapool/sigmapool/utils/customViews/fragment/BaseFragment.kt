package org.sigmapool.sigmapool.utils.customViews.fragment

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import org.sigmapool.api.hasConnection
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.utils.interfaces.IBrowserVm
import org.sigmapool.sigmapool.utils.vm.ErrorHandleVm

abstract class BaseFragment : Fragment() {

    open fun setUpVm(vm: ViewModel, binding: ViewDataBinding) {
        binding.lifecycleOwner = this

        if (vm is ErrorHandleVm) {
            vm.errorLiveData.observe(this, Observer { toast(it) })
        }

        if (vm is IBrowserVm) {
            vm.urlLiveData.observe(this, Observer { openUrl(it) })
        }
    }

    private fun toast(text: String) {
        Toast.makeText(context, getErrorText(text), Toast.LENGTH_SHORT).show()
    }

    private fun openUrl(url: String) {
        if (url.isEmpty()) return

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    private fun getErrorText(text: String): String {
        context.let {
            if (hasConnection(context!!)) {
                context?.getString(R.string.no_connection)
            }
        }
        return text
    }
}