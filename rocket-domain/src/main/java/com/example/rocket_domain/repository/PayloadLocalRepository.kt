package com.example.rocket_domain.repository

import com.example.rocket_domain.model.local.Payload
import io.reactivex.Single

interface PayloadLocalRepository {
    fun insertList(payloads: List<Payload>)

    fun getList(): Single<List<Payload>>
}