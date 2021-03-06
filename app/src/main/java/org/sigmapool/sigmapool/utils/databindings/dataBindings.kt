package org.sigmapool.sigmapool.utils.databindings

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import org.sigmapool.common.models.BlogDto
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.coin.ICoinProvider
import org.sigmapool.sigmapool.screens.home.adapter.CoinViewPagerAdapter
import org.sigmapool.sigmapool.screens.home.coin.CoinItemVM
import org.sigmapool.sigmapool.screens.home.coin.CoinVm
import org.sigmapool.sigmapool.utils.customViews.slider.MainSliderAdapter
import org.sigmapool.sigmapool.utils.customViews.spinner.CustomAdapter
import org.sigmapool.sigmapool.utils.customViews.viewPager.FragmentViewPager
import org.sigmapool.sigmapool.utils.interfaces.OnTextWatcherVm
import ss.com.bannerslider.Slider
import ss.com.bannerslider.event.OnSlideClickListener


@BindingAdapter("app:onNavigationItemSelected")
fun onNavigationItemSelected(
    view: BottomNavigationView,
    listener: BottomNavigationView.OnNavigationItemSelectedListener
) {
    view.setOnNavigationItemSelectedListener(listener)
}

@BindingAdapter("app:initCoinAdapter", "app:fragmentManager", requireAll = true)
fun initCoinAdapter(
    viewPager: ViewPager,
    coins: ArrayList<CoinItemVM>?,
    fragmentManager: FragmentManager
) {
    if (coins.isNullOrEmpty()) return

    viewPager.setPadding(20, 0, 20, 0)
    viewPager.pageMargin = -50
    viewPager.adapter = CoinViewPagerAdapter(coins, fragmentManager)
}

@BindingAdapter("app:src")
fun imageMipmapSrc(view: ImageView, mipmapRes: Int) {
    view.setImageResource(mipmapRes)
}

@BindingAdapter("app:isPagingEnabled")
fun isPagingEnabled(view: FragmentViewPager, isPagingEnabled: Boolean) {
    view.isPagingEnabled = isPagingEnabled
}

@BindingAdapter("app:isWrapHeightEnabled")
fun isWrapHeightEnabled(view: FragmentViewPager, isWrapHeightEnabled: Boolean) {
    view.isWrapHeightEnabled = isWrapHeightEnabled
}

@BindingAdapter("bind:pager")
fun bindViewPagerTabs(view: TabLayout, pagerView: ViewPager) {
    view.setupWithViewPager(pagerView, true)
}

@BindingAdapter("app:src")
fun loadIcon(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Picasso.get().load(url).into(view)
    }
}

@BindingAdapter("app:loadMinerIcon")
fun loadMinerIcon(view: ImageView, url: String) {
    val localUrl = if (url.isEmpty()) "default" else url
    Picasso.get().load(localUrl)
        .placeholder(R.mipmap.ic_ant)
        .error(R.mipmap.ic_ant)
        .into(view)
}

@BindingAdapter("app:setImages", "app:onImageClick", requireAll = true)
fun setImages(slider: Slider, items: ArrayList<BlogDto>?, listener: OnSlideClickListener?) {
    items.let {
        val adapter = it?.let { it1 -> MainSliderAdapter(it1) }
        slider.setAdapter(adapter)
        if (adapter != null) {
            slider.setInterval(10000)
            slider.setOnSlideClickListener(listener)
        }
    }
}

@BindingAdapter("app:onActivatedChange")
fun onActivatedChange(view: View, isActivated: Boolean) {
    view.isActivated = isActivated
}

@BindingAdapter("rotateOnClick")
fun rotateOnClick(view: View, listener: View.OnClickListener) {
    val animation = view.animation
    view.setOnClickListener {
        if (animation == null) {
            view.startAnimation(createAnimation())
        } else {
            animation.cancel()
            view.animation = null
        }
        listener.onClick(view)
    }
}

private fun createAnimation(): Animation {
    val anim = RotateAnimation(
        0f,
        360f,
        Animation.RELATIVE_TO_SELF,
        0.5f,
        Animation.RELATIVE_TO_SELF,
        0.5f
    )
    anim.duration = 1400

    return anim
}

@BindingAdapter("bind:initCoinSpinner", "bind:initCoins")
fun initCoinSpinner(spinner: Spinner, coinProvider: ICoinProvider, coins: ArrayList<CoinVm>) {
    val adapter = CustomAdapter(spinner, coinProvider, coins)
    spinner.adapter = adapter
    coinProvider.addOnChangeListener {
        adapter.refresh()
    }
}

@BindingAdapter("app:textWatcher")
fun textWatcher(view: EditText, textWatcher: OnTextWatcherVm?) {
    view.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            val text = s.toString()
            if (text.contains(',')) {
                view.setText(text.replace(',', '.'))
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            textWatcher?.onTextChanged(s, start, before, count)
        }
    })
}

@BindingAdapter("app:wrappedText")
fun wrappedText(view: AppCompatTextView, textRes: Int) {
    val text = view.context.getString(textRes)
    view.text = "$text"
}
