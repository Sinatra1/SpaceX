package com.vladislav.shumilov.core_ui.utils

import android.view.View
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
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