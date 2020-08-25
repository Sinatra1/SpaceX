package com.vladislav.shumilov.core_ui.utils

import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.request.ImageRequestBuilder
import com.vladislav.shumilov.core_ui.R


@BindingAdapter("visibilityEqualsVisible")
fun visibilityEqualsVisible(view: View, visible: Boolean) {
    view.setVisibility(if (visible) View.VISIBLE else View.GONE)
}

@BindingAdapter("textLink")
fun showTextLink(view: TextView, text: String?) {
    text?.let {
        view.text = HtmlCompat.fromHtml(
            view.context.resources.getString(R.string.text_link, text),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
    }
}

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