package com.vladislav.shumilov.launch_ui.ui.list_with_detail

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.ui.NavigationUI
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_ui.ui.activity.SingleActivity
import com.vladislav.shumilov.core_ui.ui.list_with_detail.BaseListWithDetailFragment
import com.vladislav.shumilov.launch_ui.R
import com.vladislav.shumilov.launch_ui.ui.detail.LaunchDetailFragment
import com.vladislav.shumilov.launch_ui.ui.list.LaunchesListFragment
import kotlinx.android.synthetic.main.launches_list_with_detail.*

private const val LIST_FRAGMENT_TAG = "launches_list_fragment"
private const val DETAIL_FRAGMENT_TAG = "launch_detail_fragment"

@FragmentScope
internal class LaunchesListWithDetailFragment :
    BaseListWithDetailFragment<LaunchesListFragment, LaunchDetailFragment>(), LifecycleObserver {

    override val layoutId = R.layout.launches_list_with_detail

    override fun getListFragment(): LaunchesListFragment =
        LaunchesListFragment.newInstance()

    override fun getDetailFragmentTag(itemId: String): LaunchDetailFragment =
        LaunchDetailFragment.newInstance(itemId)

    override fun getListFragmentTag() = LIST_FRAGMENT_TAG

    override fun getDetailFragmentTag() = DETAIL_FRAGMENT_TAG

    override fun onAttach(context: Context) {
        super.onAttach(context)

        activity?.lifecycle?.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreateActivity() {
        context?.let { context ->
            (activity as? SingleActivity)?.getNavigationController()?.let {
                NavigationUI.setupWithNavController(
                    launchesListWithDetailToolbar,
                    it
                )
            }
        }
    }

    override fun onDetach() {
        activity?.lifecycle?.removeObserver(this)

        super.onDetach()
    }
}