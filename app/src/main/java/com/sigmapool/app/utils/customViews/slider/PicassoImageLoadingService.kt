package com.sigmapool.app.utils.customViews.slider

import android.content.Context
import android.widget.ImageView
import com.sigmapool.app.R
import com.squareup.picasso.Picasso
import ss.com.bannerslider.ImageLoadingService

class PicassoImageLoadingService(val context: Context) : ImageLoadingService {

    override fun loadImage(url: String?, imageView: ImageView?) {
        Picasso.get().load(url)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(imageView)
    }

    override fun loadImage(resource: Int, imageView: ImageView?) {
        Picasso.get().load(resource)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(imageView)
    }

    override fun loadImage(url: String?, placeHolder: Int, errorDrawable: Int, imageView: ImageView?) {
        Picasso.get().load(url)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(imageView)
    }
}