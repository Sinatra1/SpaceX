package com.example.rocket_domain.model.remote

interface SecondStageResponse<P : PayloadResponse<*>> {
    val block: Int?
    val payloads: List<P>?
}