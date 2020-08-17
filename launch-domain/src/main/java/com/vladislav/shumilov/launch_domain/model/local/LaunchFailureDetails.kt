package com.vladislav.shumilov.launch_domain.model.local

interface LaunchFailureDetails {
    var id: String
    var launchId: String
    var time: Int?
    var altitude: String?
    var reason: String?
}