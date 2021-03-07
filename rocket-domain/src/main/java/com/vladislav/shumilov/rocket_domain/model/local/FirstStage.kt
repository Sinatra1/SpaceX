package com.vladislav.shumilov.rocket_domain.model.local

interface FirstStage {
    var id: String
    var rocketId: String
    var cores: List<Core>?
}