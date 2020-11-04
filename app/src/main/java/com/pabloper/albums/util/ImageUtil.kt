package com.pabloper.albums.util

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun loadRemoteImage(imageView: ImageView, url: String?) {
    Picasso.get()
        .load(url)
        .placeholder(android.R.drawable.ic_media_play)
        .into(imageView)
}