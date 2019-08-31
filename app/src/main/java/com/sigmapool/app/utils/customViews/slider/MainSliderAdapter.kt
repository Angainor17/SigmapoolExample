package com.sigmapool.app.utils.customViews.slider

import com.sigmapool.common.models.BlogDto
import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder


class MainSliderAdapter(private val items: ArrayList<BlogDto>) : SliderAdapter() {

    override fun getItemCount(): Int = items.size

    override fun onBindImageSlide(position: Int, viewHolder: ImageSlideViewHolder?) {
        val item = items[position]

        viewHolder?.bindImageSlide(item.imageUrl)
    }
}