package com.vladislav.shumilov.launch_domain.model.remote

interface LaunchFailureDetailsResponse {
    val time: Int?
    val altitude: String?
    val reason: String?
}