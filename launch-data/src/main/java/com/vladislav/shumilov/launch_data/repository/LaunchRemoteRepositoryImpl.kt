package com.vladislav.shumilov.launch_data.repository

import com.example.rocket_data.repository.RocketRemoteRepositoryImpl
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_data.util.generateRandomId
import com.vladislav.shumilov.launch_data.api.LaunchApi
import com.vladislav.shumilov.launch_data.model.local.LaunchImpl
import com.vladislav.shumilov.launch_data.model.remote.LaunchResponseImpl
import com.vladislav.shumilov.launch_domain.repository.LaunchRemoteRepository
import io.reactivex.Single
import javax.inject.Inject

@FragmentScope
class LaunchRemoteRepositoryImpl @Inject constructor(
    private val launchApi: LaunchApi,
    private val rocketRemoteRepository: RocketRemoteRepositoryImpl,
    private val launchSiteRemoteRepository: LaunchSiteRemoteRepositoryImpl,
    private val launchFailureDetailsRemoteRepository: LaunchFailureDetailsRemoteRepositoryImpl,
    private val linksRemoteRepository: LinksRemoteRepositoryImpl
) :
    LaunchRemoteRepository<LaunchResponseImpl, LaunchImpl> {
    override fun getList(): Single<List<LaunchResponseImpl>> = launchApi.getList()

    override fun responsesToModels(launchResponses: List<LaunchResponseImpl>): List<LaunchImpl> {
        val launches = ArrayList<LaunchImpl>()

        launchResponses.forEach {
            launches.add(responseToModel(it))
        }

        return launches
    }

    override fun responseToModel(launchResponse: LaunchResponseImpl): LaunchImpl {

        val launchId = generateId()

        return LaunchImpl(
            launchId,
            launchResponse.flight_number,
            launchResponse.upcoming,
            launchResponse.launch_year,
            launchResponse.launch_date_unix,
            launchResponse.launch_date_utc,
            launchResponse.is_tentative,
            launchResponse.tentative_max_precision,
            launchResponse.tbd,
            launchResponse.launch_window,
            launchResponse.rocket?.rocket_id,
            launchResponse.rocket?.let { rocketRemoteRepository.responseToModel(it) },
            launchResponse.launch_site?.site_id,
            launchResponse.launch_site?.let { launchSiteRemoteRepository.responseToModel(it) },
            launchResponse.launch_success,
            launchResponse.launch_failure_details?.let {
                launchFailureDetailsRemoteRepository.responseToModel(it, launchId)
            },
            launchResponse.links?.let { linksRemoteRepository.responseToModel(it, launchId) },
            launchResponse.details,
            launchResponse.static_fire_date_utc,
            launchResponse.static_fire_date_unix
        )
    }

    override fun generateId() = generateRandomId()
}