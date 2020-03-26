package com.vladislav.shumilov.launch_domain.model

interface SecondStageResponse<P : PayloadResponse<*>> {
    val block: Int?
    val payloads: List<P>?
}