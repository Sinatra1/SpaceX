package com.vladislav.shumilov.core_ui.ui.list_with_detail

import android.view.View

interface BaseListFragment {
    fun showDetailFragment(itemId: String, transitionView: View? = null)

    fun selectListRow(itemId: String)
}