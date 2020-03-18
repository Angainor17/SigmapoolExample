package org.sigmapool.sigmapool.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.utils.customViews.ColoredToolbarActivity

class ContainerActivity : ColoredToolbarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extras = intent?.extras!!

        val fragment = Class.forName(extras.getString(FRAGMENT_TAG, "")).newInstance() as Fragment
        fragment.arguments = extras.getBundle(BUNDLE_TAG) ?: Bundle()

        setContentView(R.layout.activity_container)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}