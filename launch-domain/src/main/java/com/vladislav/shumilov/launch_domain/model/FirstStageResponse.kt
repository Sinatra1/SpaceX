package com.vladislav.shumilov.launch_domain.model

interface FirstStageResponse<C : CoreResponse> {
    val cores: List<C>?
}