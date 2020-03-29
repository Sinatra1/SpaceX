package com.example.rocket_domain.model.remote

interface FirstStageResponse<C : CoreResponse> {
    val cores: List<C>?
}