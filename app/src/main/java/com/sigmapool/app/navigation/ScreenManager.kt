package com.sigmapool.app.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment

const val BUNDLE_TAG = "bundle"
const val FRAGMENT_TAG = "fragment"

fun showScreen(context: Context, clazz: Class<out Fragment>, bundle: Bundle = Bundle()) {
    val intent = Intent(context, ContainerActivity::class.java)
    intent.putExtra(BUNDLE_TAG, bundle)
    intent.putExtra(FRAGMENT_TAG, clazz.canonicalName)

    context.startActivity(intent)
}