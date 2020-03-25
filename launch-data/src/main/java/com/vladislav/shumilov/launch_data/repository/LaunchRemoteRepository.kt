package com.vladislav.shumilov.launch_data.repository

import com.vladislav.shumilov.launch_data.LaunchScope
import com.vladislav.shumilov.launch_data.api.LaunchApi
import com.vladislav.shumilov.launch_domain.model.LaunchResponse
import com.vladislav.shumilov.launch_domain.repository.LaunchRepository
import io.reactivex.Single
import javax.inject.Inject

@LaunchScope
class LaunchRemoteRepository @Inject constructor(private val launchApi: LaunchApi) :
    LaunchRepository {
    override fun getList(): Single<List<LaunchResponse>> = launchApi.getList()
}