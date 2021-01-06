package com.vladislav.shumilov.core_ui.utils

import android.content.Context
import com.vladislav.shumilov.core_ui.R

fun isTabletDevice(context: Context): Boolean = context.resources.getBoolean(R.bool.isTablet)