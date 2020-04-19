package com.rascarlo.astroeye.recents

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rascarlo.astroeye.R

@BindingAdapter("bindImage")
fun bindImage(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUrl = imgUrl.toUri().buildUpon().scheme("http").build()
        Glide.with(imageView.context)
            .load(imgUrl)
            .centerCrop()
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_broken_image_black_24dp)
                    .error(R.drawable.ic_broken_image_black_24dp)
            )
            .into(imageView)
    }
}

@BindingAdapter("bindText")
fun bindText(textView: TextView, text: String?) {
    when {
        text != null -> {
            textView.text = text
        }
        else -> {
            textView.text = textView.context.getString(R.string.not_available)
        }
    }
}