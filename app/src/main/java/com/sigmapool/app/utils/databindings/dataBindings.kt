package com.sigmapool.app.utils.databindings

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.sigmapool.app.R
import com.sigmapool.app.screens.bottomSheetScreen.ViewPagerAdapter
import com.sigmapool.app.screens.bottomSheetScreen.ViewPagerScreen
import com.sigmapool.app.screens.calculator.adapter.CalcTabAdapter
import com.sigmapool.app.screens.calculator.viewModel.CalcItemVM
import com.sigmapool.app.screens.home.adapter.CoinViewPagerAdapter
import com.sigmapool.app.screens.home.coin.CoinsVM
import com.sigmapool.app.screens.poolInfo.adapters.PoolInfoFragmentPagerAdapter
import com.sigmapool.app.screens.settings.viewModel.CoinToolbarVM
import com.sigmapool.app.screens.workers.adapter.WorkerViewPagerAdapter
import com.sigmapool.app.screens.workers.viewModel.WorkersVM
import com.sigmapool.app.utils.customViews.slider.MainSliderAdapter
import com.sigmapool.app.utils.customViews.spinner.CustomAdapter
import com.sigmapool.app.utils.customViews.viewPager.FragmentViewPager
import com.sigmapool.app.utils.interfaces.OnTextWatcherVm
import com.sigmapool.common.models.BlogDto
import com.squareup.picasso.Picasso
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
fun initCoinAdapter(viewPager: ViewPager, vm: CoinsVM, fragmentManager: FragmentManager) {
    viewPager.setPadding(20, 0, 20, 0)
    viewPager.pageMargin = -50
    viewPager.adapter = CoinViewPagerAdapter(vm.getCoins(), fragmentManager)
}

@BindingAdapter("app:initWorkerAdapter", "app:fragmentManager", requireAll = true)
fun initWorkerAdapter(view: ViewPager, vm: WorkersVM, fragmentManager: FragmentManager) {
    view.offscreenPageLimit = 4
    view.adapter = WorkerViewPagerAdapter(vm.getWorkerLists(), fragmentManager)
}

@BindingAdapter("app:viewPagerAdapter")
fun viewPagerAdapter(view: FragmentViewPager, fragmentManager: FragmentManager) {
    view.offscreenPageLimit = 5
    view.adapter = ViewPagerAdapter(fragmentManager)
}

@BindingAdapter("app:initCalcAdapter", "app:viewCalcTabAdapter")
fun viewCalcTabAdapter(
    view: FragmentViewPager,
    items: ArrayList<CalcItemVM>,
    fragmentManager: FragmentManager
) {
    view.offscreenPageLimit = 2
    view.adapter = CalcTabAdapter(items, fragmentManager)
}

@BindingAdapter("app:initPoolInfoAdapter", "app:fragments")
fun initPoolInfoAdapter(
    view: FragmentViewPager,
    fragmentManager: FragmentManager,
    fragments: ArrayList<Fragment>
) {
    view.offscreenPageLimit = 2
    view.adapter = PoolInfoFragmentPagerAdapter(
        fragments,
        fragmentManager
    )
}

@BindingAdapter("app:onScreenChange")
fun onScreenChange(view: FragmentViewPager, screen: ViewPagerScreen) {
    if (screen.smoothScroll) {
        view.currentItem = screen.position
    } else {
        view.setCurrentItem(screen.position, false)
    }
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

@BindingAdapter("app:loadMinerIcon")
fun loadMinerIcon(view: ImageView, url: String) {
    Picasso.get().load(url)
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

@BindingAdapter("bind:initCoinSpinner")
fun initCoinSpinner(spinner: Spinner, vm: CoinToolbarVM) {
    val adapter = CustomAdapter(spinner, vm, vm.coinProvider.coins)
    spinner.adapter = adapter
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
