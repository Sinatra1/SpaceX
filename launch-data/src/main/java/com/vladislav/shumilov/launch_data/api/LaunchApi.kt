package com.vladislav.shumilov.launch_data.api

import com.vladislav.shumilov.launch_data.LaunchScope
import com.vladislav.shumilov.launch_data.model.LaunchResponseImpl
import io.reactivex.Single
import retrofit2.http.GET

@LaunchScope
interface LaunchApi {
    @GET("launches")
    fun getList(): Single<List<LaunchResponseImpl>>
}