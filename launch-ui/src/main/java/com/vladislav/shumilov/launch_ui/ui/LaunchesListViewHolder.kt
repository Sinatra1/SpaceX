package com.vladislav.shumilov.launch_ui.ui

import android.net.Uri
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.example.launch_ui.databinding.LaunchesListRowBinding
import com.facebook.imagepipeline.request.ImageRequest.RequestLevel
import com.facebook.imagepipeline.request.ImageRequestBuilder
import com.vladislav.shumilov.launch_domain.model.local.LaunchWithMissions


internal const val LAUNCHES_LIST_VIEW_HOLDER_TYPE = 101

internal class LaunchesListViewHolder(private val binding: LaunchesListRowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val launchWithMissions = ObservableField<LaunchWithMissions>()

    fun bind(item: LaunchWithMissions) {
        this.launchWithMissions.set(item)

        binding.viewHolder = this
        binding.executePendingBindings()

        setMissionIcon(item)
    }

    private fun setMissionIcon(item: LaunchWithMissions) =
        if (item.links?.missionPatchSmall != null) {
            val request = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(item.links?.missionPatchSmall))
                .setLocalThumbnailPreviewsEnabled(false)
                .setLowestPermittedRequestLevel(RequestLevel.FULL_FETCH)
                .setProgressiveRenderingEnabled(false)
                .build()

            binding.missionIcon.setImageRequest(request)
        } else {
            binding.missionIcon.setImageDrawable(binding.missionIcon.topLevelDrawable)
        }
}