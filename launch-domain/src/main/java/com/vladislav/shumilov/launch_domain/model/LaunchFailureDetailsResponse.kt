package com.vladislav.shumilov.launch_domain.model

interface LaunchFailureDetailsResponse {
    var time: Int?
    var altitude: String?
    var reason: String?
}