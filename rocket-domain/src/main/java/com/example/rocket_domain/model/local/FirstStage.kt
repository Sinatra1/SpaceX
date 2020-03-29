package com.example.rocket_domain.model.local

interface FirstStage<C : Core> {
    var id: String
    var rocket_id: String
    /*var cores: List<C>?*/
}