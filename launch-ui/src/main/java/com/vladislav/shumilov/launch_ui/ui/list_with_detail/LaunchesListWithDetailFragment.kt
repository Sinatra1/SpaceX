package com.vladislav.shumilov.launch_ui.ui.list_with_detail

import com.vladislav.shumilov.core_ui.ui.list_with_detail.BaseListWithDetailFragment
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_ui.ui.detail.LaunchDetailFragment
import com.vladislav.shumilov.launch_ui.ui.list.LaunchesListFragment

private const val LIST_FRAGMENT_TAG = "launches_list_fragment"
private const val DETAIL_FRAGMENT_TAG = "launch_detail_fragment"

@FragmentScope
class LaunchesListWithDetailFragment :
    BaseListWithDetailFragment<LaunchesListFragment, LaunchDetailFragment>() {

    override fun getListFragment(): LaunchesListFragment =
        LaunchesListFragment.newInstance()

    override fun getDetailFragmentTag(itemId: String): LaunchDetailFragment =
        LaunchDetailFragment.newInstance(itemId)

    override fun getListFragmentTag() = LIST_FRAGMENT_TAG

    override fun getDetailFragmentTag() = DETAIL_FRAGMENT_TAG
}