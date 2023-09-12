package com.vladislav.shumilov.launch_ui.models

import androidx.annotation.VisibleForTesting
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList

@VisibleForTesting
internal const val START_OFFSET = 0

internal data class LaunchesListState(
    val isInProgress: Boolean = false,
    val isShownRefreshingIcon: Boolean = false,
    val isLastPage: Boolean = false,
    val launches: List<LaunchForList> = emptyList(),
    val offset: Int = START_OFFSET,
)