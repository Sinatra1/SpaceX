package com.vladislav.shumilov.core_ui.utils

import androidx.lifecycle.LiveData

interface OnClickViewHolder<T> {

    fun getViewHolderClickEvent(): LiveData<T>
}