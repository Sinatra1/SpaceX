package com.vladislav.shumilov.core_ui.utils

import android.net.Uri
import androidx.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.request.ImageRequestBuilder


@BindingAdapter("fresco:actualImageUri")
fun SimpleDraweeView.setActualImageUriByBinding(imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) {
        setImageDrawable(topLevelDrawable)
    } else {
        val request = ImageRequestBuilder
            .newBuilderWithSource(Uri.parse(imageUrl))
            .setLocalThumbnailPreviewsEnabled(false)
            .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.FULL_FETCH)
            .setProgressiveRenderingEnabled(false)
            .build()

        setImageRequest(request)
    }
}