package com.hellmann.bluecoding.util.extensions

import android.widget.ImageView
import com.hellmann.bluecoding.util.GlideApp

fun ImageView.load(url: String?) = GlideApp.with(this).load(url).into(this)
