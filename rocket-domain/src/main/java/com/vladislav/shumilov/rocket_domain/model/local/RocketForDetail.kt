package com.vladislav.shumilov.rocket_domain.model.local

interface RocketForDetail {
    val rocket: Rocket
    val firstStage: FirstStageForDetail?
    val secondStage: SecondStageForDetail?
    val fairings: Fairings?
}