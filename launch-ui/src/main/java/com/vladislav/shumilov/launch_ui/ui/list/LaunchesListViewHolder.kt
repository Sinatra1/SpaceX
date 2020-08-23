package com.vladislav.shumilov.launch_ui.ui.list

import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.example.launch_ui.databinding.LaunchesListRowBinding
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList
import io.reactivex.subjects.Subject


internal const val LAUNCHES_LIST_VIEW_HOLDER_TYPE = 101

internal class LaunchesListViewHolder(private val binding: LaunchesListRowBinding, private val onClickViewHolderCallback: Subject<LaunchForList>) :
    RecyclerView.ViewHolder(binding.root) {

    val launchForList = ObservableField<LaunchForList>()

    fun bind(item: LaunchForList) {
        this.launchForList.set(item)

        binding.viewHolder = this
        binding.executePendingBindings()
    }

    fun onLaunchClick() {
        launchForList.get()?.let(onClickViewHolderCallback::onNext)
    }
}