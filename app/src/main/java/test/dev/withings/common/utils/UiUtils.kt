package test.dev.withings.common.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


infix fun ImageView.load(imageUrl: String?) = Glide
    .with(context)
    .load(imageUrl)
    .fitCenter()
    .transition(DrawableTransitionOptions.withCrossFade())
    .into(this)