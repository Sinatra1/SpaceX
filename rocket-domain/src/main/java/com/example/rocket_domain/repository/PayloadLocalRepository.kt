package com.example.rocket_domain.repository

import com.example.rocket_domain.model.local.Payload
import io.reactivex.Single

interface PayloadLocalRepository<T : Payload<*>> {
    fun insertList(launches: List<T>)

    fun getList(): Single<List<T>>
}