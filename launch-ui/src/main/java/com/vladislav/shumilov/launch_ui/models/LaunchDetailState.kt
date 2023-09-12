package com.vladislav.shumilov.launch_ui.models

import com.vladislav.shumilov.launch_domain.model.local.LaunchForDetail

internal data class LaunchDetailState(
    val launch: LaunchForDetail? = null,
)