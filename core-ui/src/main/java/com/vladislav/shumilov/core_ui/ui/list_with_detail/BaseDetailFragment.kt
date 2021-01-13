package com.vladislav.shumilov.core_ui.ui.list_with_detail

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.vladislav.shumilov.core_ui.ui.activity.SingleActivity
import com.vladislav.shumilov.core_ui.utils.isLandscape

abstract class BaseDetailFragment : Fragment(), LifecycleObserver {

    override fun onAttach(context: Context) {
        super.onAttach(context)

        activity?.lifecycle?.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreateActivity() {
        context?.let {
            if (isLandscape(it) && parentFragment !is BaseListWithDetailFragment<*, *>) {
                (activity as? SingleActivity)?.popBackStack()
            }
        }
    }

    override fun onDetach() {
        activity?.lifecycle?.removeObserver(this)

        super.onDetach()
    }
}