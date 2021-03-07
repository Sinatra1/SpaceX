package com.vladislav.shumilov.rocket_domain.model.local

interface SecondStage {
    var id: String
    var rocketId: String
    var block: Int?
    var payloads: List<Payload>?
}