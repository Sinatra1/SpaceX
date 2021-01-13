package com.vladislav.shumilov.core_ui.ui.list_with_detail

import android.view.View

interface BaseListWithDetail {
    fun initializeSelectedItemId(itemId: String, transitionView: View?)

    fun transmitSelectedItemId(itemId: String, transitionView: View? = null)

    fun getSelectedItemId(): String?
}