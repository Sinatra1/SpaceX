package com.vladislav.shumilov.rocket_domain.model.remote

interface SecondStageResponse {
    val block: Int?
    val payloads: List<PayloadResponse>?
}