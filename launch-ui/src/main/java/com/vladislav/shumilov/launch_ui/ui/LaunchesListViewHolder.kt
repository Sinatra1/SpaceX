package com.vladislav.shumilov.launch_ui.ui

import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.example.launch_ui.databinding.LaunchesListRowBinding
import com.vladislav.shumilov.launch_data.model.local.LaunchWithMissionsImpl

internal const val LAUNCHES_LIST_VIEW_HOLDER_TYPE = 101

internal class LaunchesListViewHolder(private val binding: LaunchesListRowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val launchWithMissions = ObservableField<LaunchWithMissionsImpl>()

    fun bind(item: LaunchWithMissionsImpl) {
        this.launchWithMissions.set(item)
        binding.viewHolder = this
        binding.executePendingBindings()
    }
}