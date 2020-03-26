package com.vladislav.shumilov.launch_domain.model

interface RocketResponse<F : FirstStageResponse<*>, S : SecondStageResponse<*>, FA : FairingsResponse> {

    val rocket_id: String?
    val rocket_name: String?
    val rocket_type: String?
    val first_stage: F?
    val second_stage: S?
    val fairings: FA?
}