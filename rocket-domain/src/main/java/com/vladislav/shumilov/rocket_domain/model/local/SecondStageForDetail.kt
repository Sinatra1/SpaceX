package com.vladislav.shumilov.rocket_domain.model.local

interface SecondStageForDetail {
    val secondStage: SecondStage
    val payloads: List<PayloadForDetail>?
}