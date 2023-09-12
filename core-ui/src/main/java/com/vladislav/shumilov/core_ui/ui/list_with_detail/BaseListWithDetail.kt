package com.vladislav.shumilov.core_ui.ui.list_with_detail

interface BaseListWithDetail {
    fun initializeSelectedItemId(itemId: String)

    fun transmitSelectedItemId(itemId: String)

    fun getSelectedItemId(): String?
}