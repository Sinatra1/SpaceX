package com.example.rocket_domain.model.remote

interface FairingsResponse {
    val reused: Boolean
    val recovery_attempt: Boolean
    val recovered: Boolean
    val ship: String?
}