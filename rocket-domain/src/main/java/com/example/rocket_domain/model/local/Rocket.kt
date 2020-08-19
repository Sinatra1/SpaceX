package com.example.rocket_domain.model.local

interface Rocket {
    var id: String
    var name: String?
    var type: String?
    var firstStage: FirstStage?
    var secondStage: SecondStage?
    var fairings: Fairings?
}