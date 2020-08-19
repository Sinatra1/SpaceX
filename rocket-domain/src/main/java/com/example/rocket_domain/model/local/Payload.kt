package com.example.rocket_domain.model.local

interface Payload {
    var id: String
    var noradId: List<String>?
    var reused: Boolean
    var customers: List<String>?
    var nationality: String?
    var manufacturer: String?
    var type: String?
    var massKg: Float?
    var massLbs: Float?
    var orbit: String?
    var orbitParams: OrbitParams?
}