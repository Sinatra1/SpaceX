package com.vladislav.shumilov.launch_ui.ui.list

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList
import com.vladislav.shumilov.launch_ui.databinding.LaunchesListRowBinding

internal const val LAUNCHES_LIST_VIEW_HOLDER_TYPE = 101

internal class LaunchesListViewHolder(
    private val binding: LaunchesListRowBinding,
    private val viewHolderClickEvent: MutableLiveData<Pair<View, LaunchForList>>
) : RecyclerView.ViewHolder(binding.root) {

    var launchForList: LaunchForList? = null

    fun bind(item: LaunchForList) {
        launchForList = item

        binding.viewHolder = this
        binding.executePendingBindings()
    }

    fun onLaunchClick() {
        launchForList?.let{
            viewHolderClickEvent.postValue(binding.missionIcon to it)
        }
    }
}