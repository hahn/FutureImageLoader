package com.example.futureimageloader.util

import android.widget.ImageView
import com.example.futureimageloader.R

fun ImageView.loadImageFromUrl(url: String) {
    GlideApp.with(context)
        .load(url)
        .placeholder(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_baseline_broken_image_24)
        .into(this)
}