package com.vladislav.shumilov.rocket_domain.model.remote

interface RocketResponse {
    val rocket_id: String
    val rocket_name: String?
    val rocket_type: String?
    val first_stage: FirstStageResponse?
    val second_stage: SecondStageResponse?
    val fairings: FairingsResponse?
}