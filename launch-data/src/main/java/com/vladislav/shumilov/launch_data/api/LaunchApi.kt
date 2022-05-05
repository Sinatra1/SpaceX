package com.vladislav.shumilov.launch_data.api

import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_data.model.local.LaunchImpl
import com.vladislav.shumilov.launch_data.model.remote.LaunchResponseImpl
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

const val LAUNCHES_LIMIT = 10
internal const val LAUNCHES_SORT = LaunchImpl.Columns.FLIGHT_NUMBER
private const val LAUNCHES_ORDER = "desc"
private const val LAUNCHES_OFFSET = 0

@FragmentScope
interface LaunchApi {
    @GET("launches")
    suspend fun getList(
        @Query("offset") offset: Int = LAUNCHES_OFFSET,
        @Query("limit") limit: Int = LAUNCHES_LIMIT,
        @Query("sort") sort: String = LAUNCHES_SORT,
        @Query("order") order: String = LAUNCHES_ORDER
    ): List<LaunchResponseImpl>
}