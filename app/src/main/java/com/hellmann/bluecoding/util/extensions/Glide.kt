package com.hellmann.bluecoding.util.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.request.target.ViewTarget
import com.hellmann.bluecoding.data.BuildConfig
import com.hellmann.bluecoding.util.GlideApp

fun ImageView.load(url: String?, thumb: Boolean= true): ViewTarget<ImageView, Drawable> {
    val baseUrl = if (!thumb) BuildConfig.IMAGES_URL else BuildConfig.IMAGES_URL + "/w500"
    return GlideApp.with(this)
        .load(baseUrl + url)
        .into(this)
}
