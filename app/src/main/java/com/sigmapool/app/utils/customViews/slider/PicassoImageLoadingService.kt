package com.sigmapool.app.utils.customViews.slider

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.sigmapool.app.R
import com.squareup.picasso.Picasso
import ss.com.bannerslider.ImageLoadingService

class PicassoImageLoadingService(val context: Context) : ImageLoadingService {

    private val circularProgressDrawable = CircularProgressDrawable(context)

    init {
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.setColorSchemeColors(context.resources.getColor(R.color.violet))
        circularProgressDrawable.start()
    }

    override fun loadImage(url: String?, imageView: ImageView) {
        Picasso.get().load(url)
            .placeholder(circularProgressDrawable)
            .error(circularProgressDrawable)
            .into(imageView)
    }

    override fun loadImage(resource: Int, imageView: ImageView?) {
        Picasso.get().load(resource)
            .placeholder(circularProgressDrawable)
            .error(circularProgressDrawable)
            .into(imageView)
    }

    override fun loadImage(
        url: String?,
        placeHolder: Int,
        errorDrawable: Int,
        imageView: ImageView?
    ) {
        Picasso.get().load(url)
            .placeholder(placeHolder)
            .error(errorDrawable)
            .into(imageView)
    }
}