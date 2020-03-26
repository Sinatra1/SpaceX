package com.vladislav.shumilov.launch_domain.model

interface FairingsResponse {
    val reused: Boolean
    val recovery_attempt: Boolean
    val recovered: Boolean
    val ship: String?
}