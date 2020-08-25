package com.vladislav.shumilov.core_ui.utils

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes

/**
 * Get custom attr from current theme, if it isn't initialized
 * returned null value
 *
 * @param attr Custom attribute to check
 * @return Id res for current theme or null
 */
@JvmOverloads
fun Context.getDataFromAttrOrNull(@AttrRes attr: Int, resolveRefs: Boolean = true): Int? {
    return TypedValue().takeIf { theme.resolveAttribute(attr, it, resolveRefs) }?.data
}