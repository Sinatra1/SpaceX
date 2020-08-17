package com.example.rocket_domain.model.local

interface SecondStage<P : Payload<*>> {
    var id: String
    var rocketId: String
    var block: Int?
    var payloads: List<P>?
}