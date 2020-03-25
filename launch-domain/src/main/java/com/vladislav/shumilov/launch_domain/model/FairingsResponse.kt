package com.vladislav.shumilov.launch_domain.model

interface FairingsResponse {
    var reused: Boolean
    var recovery_attempt: Boolean
    var recovered: Boolean
    var ship: String?
}