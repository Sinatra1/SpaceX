package com.vladislav.shumilov.launch_domain.model

interface RocketResponse {

    var rocket_id: String?
    var rocket_name: String?
    var rocket_type: String?
    var first_stage: FirstStageResponse?
    var second_stage: SecondStageResponse?
    var fairings: FairingsResponse?
}