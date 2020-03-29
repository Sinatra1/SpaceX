package com.vladislav.shumilov.launch_domain.model.local

interface LaunchFailureDetails {
    var id: String
    var launch_id: String
    var time: Int?
    var altitude: String?
    var reason: String?
}