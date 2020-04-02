package com.vladislav.shumilov.mission_domain.model.local

interface Mission {
    var id: String
    var name: String?
    var wikipedia: String?
    var website: String?
    var twitter: String?
    var description: String?
}