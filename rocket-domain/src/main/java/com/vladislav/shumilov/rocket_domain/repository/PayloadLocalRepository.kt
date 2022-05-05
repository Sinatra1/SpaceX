package com.vladislav.shumilov.rocket_domain.repository

import com.vladislav.shumilov.rocket_domain.model.local.Payload

interface PayloadLocalRepository {
    fun insertList(payloads: List<Payload>)

    suspend fun getList(): List<Payload>
}