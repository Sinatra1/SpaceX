package com.vladislav.shumilov.launch_data.repository

import com.example.rocket_data.model.local.RocketImpl
import com.example.rocket_domain.repository.RocketRemoteRepositoryAlias
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_data.util.generateRandomId
import com.vladislav.shumilov.launch_data.api.LaunchApi
import com.vladislav.shumilov.launch_data.model.local.LaunchFailureDetailsImpl
import com.vladislav.shumilov.launch_data.model.local.LaunchImpl
import com.vladislav.shumilov.launch_data.model.local.LaunchSiteImpl
import com.vladislav.shumilov.launch_data.model.local.LinksImpl
import com.vladislav.shumilov.launch_data.model.remote.LaunchResponseImpl
import com.vladislav.shumilov.launch_domain.repository.LaunchFailureDetailsRemoteRepositoryAlias
import com.vladislav.shumilov.launch_domain.repository.LaunchRemoteRepository
import com.vladislav.shumilov.launch_domain.repository.LaunchSiteRemoteRepositoryAlias
import com.vladislav.shumilov.launch_domain.repository.LinksRemoteRepositoryAlias
import com.vladislav.shumilov.mission_data.model.local.MissionImpl
import com.vladislav.shumilov.ship_data.model.local.ShipImpl
import io.reactivex.Single
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@FragmentScope
class LaunchRemoteRepositoryImpl @Inject constructor(
    private val launchApi: LaunchApi,
    private val rocketRemoteRepository: RocketRemoteRepositoryAlias,
    private val launchSiteRemoteRepository: LaunchSiteRemoteRepositoryAlias,
    private val launchFailureDetailsRemoteRepository: LaunchFailureDetailsRemoteRepositoryAlias,
    private val linksRemoteRepository: LinksRemoteRepositoryAlias
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
            launchResponse.launch_site?.site_id,
            launchResponse.launch_success,
            launchResponse.details,
            launchResponse.static_fire_date_utc,
            launchResponse.static_fire_date_unix
        ).apply {
            missions = prepareMissions(launchResponse)
            rocket =
                launchResponse.rocket?.let { (rocketRemoteRepository.responseToModel(it)) as RocketImpl }
            ships = prepareShips(launchResponse)
            launch_site =
                launchResponse.launch_site?.let { launchSiteRemoteRepository.responseToModel(it) as LaunchSiteImpl }
            launch_failure_details = launchResponse.launch_failure_details?.let {
                launchFailureDetailsRemoteRepository.responseToModel(
                    it,
                    launchId
                ) as LaunchFailureDetailsImpl
            }
            links =
                launchResponse.links?.let {
                    linksRemoteRepository.responseToModel(
                        it,
                        launchId
                    ) as LinksImpl
                }
        }
    }

    override fun generateId() = generateRandomId()

    private fun prepareMissions(launchResponse: LaunchResponseImpl) =
        launchResponse.mission_id?.let { missionIds ->
            val missions = ArrayList<MissionImpl>()
            val names = launchResponse.mission_name?.split("/")

            missionIds.forEachIndexed { index, missionId ->
                missions.add(MissionImpl(missionId, names?.getOrNull(index)?.trim()))
            }

            missions
        }

    private fun prepareShips(launchResponse: LaunchResponseImpl) =
        launchResponse.ships?.let { missionIds ->
            val ships = ArrayList<ShipImpl>()

            missionIds.forEachIndexed { index, shipId ->
                ships.add(ShipImpl(shipId.toUpperCase(Locale.US), shipId))
            }

            ships
        }
}