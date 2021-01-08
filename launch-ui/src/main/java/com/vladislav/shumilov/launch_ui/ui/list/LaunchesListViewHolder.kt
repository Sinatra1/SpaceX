package com.vladislav.shumilov.launch_ui.ui.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.launch_ui.databinding.LaunchesListRowBinding
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList
import io.reactivex.subjects.Subject

internal const val LAUNCHES_LIST_VIEW_HOLDER_TYPE = 101

internal class LaunchesListViewHolder(
    private val binding: LaunchesListRowBinding,
    private val onClickViewHolderCallback: Subject<Pair<View, LaunchForList>>
) : RecyclerView.ViewHolder(binding.root) {

    var launchForList: LaunchForList? = null

    fun bind(item: LaunchForList) {
        launchForList = item

        binding.viewHolder = this
        binding.executePendingBindings()
    }

    fun onLaunchClick() {
        launchForList?.let{
            onClickViewHolderCallback.onNext(binding.missionIcon to it)
        }
    }
}