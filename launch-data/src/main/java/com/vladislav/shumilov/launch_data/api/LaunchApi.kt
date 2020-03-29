package com.vladislav.shumilov.launch_data.api

import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_data.model.remote.LaunchResponseImpl
import io.reactivex.Single
import retrofit2.http.GET

@FragmentScope
interface LaunchApi {
    @GET("launches")
    fun getList(): Single<List<LaunchResponseImpl>>
}