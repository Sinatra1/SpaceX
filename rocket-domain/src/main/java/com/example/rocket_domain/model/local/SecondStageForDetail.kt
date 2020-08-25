package com.example.rocket_domain.model.local

interface SecondStageForDetail {
    val secondStage: SecondStage
    val payloads: List<PayloadForDetail>?
}