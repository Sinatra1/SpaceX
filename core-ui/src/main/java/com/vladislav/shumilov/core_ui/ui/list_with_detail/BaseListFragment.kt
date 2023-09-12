package com.vladislav.shumilov.core_ui.ui.list_with_detail

interface BaseListFragment {
    fun showDetailFragment(itemId: String)

    fun selectListRow(itemId: String)
}