package com.example.rocket_domain.model.remote

typealias FirstStageResponseAlias = FirstStageResponse<*>

interface FirstStageResponse<C : CoreResponse> {
    val cores: List<C>?
}