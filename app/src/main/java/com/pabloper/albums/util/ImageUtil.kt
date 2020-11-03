package com.pabloper.albums.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun loadRemoteImage(imageView: ImageView, url: String?) {
    Glide
        .with(imageView.context)
        .load(url)
        .centerCrop()
        .placeholder(android.R.drawable.ic_menu_mapmode)
        .into(imageView)
}