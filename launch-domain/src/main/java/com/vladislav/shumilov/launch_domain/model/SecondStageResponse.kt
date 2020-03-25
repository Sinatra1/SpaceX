package com.vladislav.shumilov.launch_domain.model

interface SecondStageResponse {
    var block: Int?
    var payloads: List<PayloadResponse>?
}