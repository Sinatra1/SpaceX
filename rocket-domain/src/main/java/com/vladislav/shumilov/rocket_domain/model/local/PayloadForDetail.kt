package com.vladislav.shumilov.rocket_domain.model.local

interface PayloadForDetail {
    val payload: Payload
    val orbitParams: OrbitParams?
}