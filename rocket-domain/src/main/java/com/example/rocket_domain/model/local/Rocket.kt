package com.example.rocket_domain.model.local

typealias RocketAlias = Rocket<*, *, *>

interface Rocket<F: FirstStage<*>, S: SecondStage<*>, FA: Fairings> {
    var id: String
    var name: String?
    var type: String?
    var first_stage: F?
    var second_stage: S?
    var fairings: FA?
}