package com.example.rocket_domain.model.local

interface Fairings {
    var id: String
    var rocketId: String
    var reused: Boolean
    var recoveryAttempt: Boolean
    var recovered: Boolean
    var ship: String?
}