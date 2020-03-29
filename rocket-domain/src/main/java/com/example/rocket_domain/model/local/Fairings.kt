package com.example.rocket_domain.model.local

interface Fairings {
    var id: String
    var rocket_id: String
    var reused: Boolean
    var recovery_attempt: Boolean
    var recovered: Boolean
    var ship: String?
}