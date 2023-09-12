package com.vladislav.shumilov.launch_ui.ui.list

import androidx.recyclerview.widget.RecyclerView
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList
import com.vladislav.shumilov.launch_ui.databinding.LaunchesListRowBinding

internal const val LAUNCHES_LIST_VIEW_HOLDER_TYPE = 101

internal class LaunchesListViewHolder(
    private val binding: LaunchesListRowBinding,
    private val onItemClickListener: (launch: LaunchForList) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    var launchForList: LaunchForList? = null

    fun bind(item: LaunchForList) {
        launchForList = item

        binding.viewHolder = this
        binding.executePendingBindings()
    }

    fun onLaunchClick() {
        launchForList?.let {
            onItemClickListener(it)
        }
    }
}