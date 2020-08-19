package com.vladislav.shumilov.launch_data.repository

import com.example.rocket_domain.repository.RocketRemoteRepository
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_data.util.UNCHECKED_CAST
import com.vladislav.shumilov.core_data.util.generateRandomId
import com.vladislav.shumilov.launch_data.api.LaunchApi
import com.vladislav.shumilov.launch_data.model.local.LaunchImpl
import com.vladislav.shumilov.launch_domain.model.local.Launch
import com.vladislav.shumilov.launch_domain.model.remote.LaunchResponse
import com.vladislav.shumilov.launch_domain.repository.*
import com.vladislav.shumilov.mission_data.model.local.MissionImpl
import com.vladislav.shumilov.ship_data.model.local.ShipImpl
import io.reactivex.Single
import java.util.*
import javax.inject.Inject
import kotlin.collections.mutableListOf

@FragmentScope
class LaunchRemoteRepositoryImpl @Inject constructor(
    private val launchApi: LaunchApi,
    private val rocketRemoteRepository: RocketRemoteRepository,
    private val launchSiteRemoteRepository: LaunchSiteRemoteRepository,
    private val launchFailureDetailsRemoteRepository: LaunchFailureDetailsRemoteRepository,
    private val linksRemoteRepository: LinksRemoteRepository
) : LaunchRemoteRepository {

    @Suppress(UNCHECKED_CAST)
    override fun getList(offset: Int, limit: Int) =
        launchApi.getList(offset, limit) as Single<List<LaunchResponse>>

    override fun responsesToModels(launchResponses: List<LaunchResponse>): List<Launch> {
        val launches = mutableListOf<Launch>()

        launchResponses.forEach {
            launches.add(responseToModel(it))
        }

        return launches
    }

    override fun responseToModel(launchResponse: LaunchResponse): Launch {

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
            launchResponse.launch_site?.site_id,
            launchResponse.launch_success,
            launchResponse.details,
            launchResponse.static_fire_date_utc,
            launchResponse.static_fire_date_unix
        ).apply {
            missions = prepareMissions(launchResponse)
            rocket =
                launchResponse.rocket?.let(rocketRemoteRepository::responseToModel)
            ships = prepareShips(launchResponse)
            launchSite =
                launchResponse.launch_site?.let(launchSiteRemoteRepository::responseToModel)
            launchFailureDetails = launchResponse.launch_failure_details?.let {
                launchFailureDetailsRemoteRepository.responseToModel(it, launchId)
            }
            links =
                launchResponse.links?.let { linksRemoteRepository.responseToModel(it, launchId) }
        }
    }

    override fun generateId() = generateRandomId()

    private fun prepareMissions(launchResponse: LaunchResponse) =
        launchResponse.mission_name?.let { missionName ->
            val missions = mutableListOf<MissionImpl>()
            val names = missionName.split("/")
            var name = ""

            names.forEach {
                name = it.trim()
                missions.add(MissionImpl(name, name))
            }

            missions
        }

    private fun prepareShips(launchResponse: LaunchResponse) =
        launchResponse.ships?.let { missionIds ->
            val ships = mutableListOf<ShipImpl>()

            missionIds.forEachIndexed { index, shipId ->
                ships.add(ShipImpl(shipId.toUpperCase(Locale.US), shipId))
            }

            ships
        }
}