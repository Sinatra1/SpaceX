package com.vladislav.shumilov.launch_ui.ui.list

import android.net.Uri
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.example.launch_ui.databinding.LaunchesListRowBinding
import com.facebook.imagepipeline.request.ImageRequest.RequestLevel
import com.facebook.imagepipeline.request.ImageRequestBuilder
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

        setMissionIcon(item)
    }

    fun onLaunchClick() {
        launchForList.get()?.let(onClickViewHolderCallback::onNext)
    }

    private fun setMissionIcon(item: LaunchForList) =
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