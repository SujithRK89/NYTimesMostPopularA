package com.srk.nytimesmostpopular.data.remote.model


import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.annotations.SerializedName
import com.srk.nytimesmostpopular.R
import kotlinx.android.parcel.Parcelize

/**
 * Created by sujithrk on 25,December,2020
 * Email sujithrk89@gmail.com
 * Copyright (c) 2020 . All rights reserved.
 */

@Parcelize
data class MediaMetadata(
    @SerializedName("format")
    val format: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
): Parcelable {
    companion object {
        @JvmStatic
        @BindingAdapter("mediaImage", "isThumb")
        fun loadImage(view: ImageView, imageUrl: String?, isThumb: Boolean) {
            if (!imageUrl.isNullOrEmpty()) {
                var option = RequestOptions.placeholderOf(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                if (isThumb) option = option.circleCrop()
                Glide.with(view.context)
                    .load(imageUrl)
                    .apply(option)
                    .into(view)
            }
        }
    }
}