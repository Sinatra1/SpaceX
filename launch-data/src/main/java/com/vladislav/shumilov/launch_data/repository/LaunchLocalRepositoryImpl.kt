package com.vladislav.shumilov.launch_data.repository

import com.example.rocket_data.model.local.RocketImpl
import com.example.rocket_domain.repository.RocketLocalRepositoryAlias
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_data.database.*
import com.vladislav.shumilov.launch_data.model.local.*
import com.vladislav.shumilov.launch_domain.repository.LaunchLocalRepository
import com.vladislav.shumilov.mission_data.database.MissionDao
import com.vladislav.shumilov.mission_data.model.local.MissionImpl
import com.vladislav.shumilov.ship_data.database.ShipDao
import com.vladislav.shumilov.ship_data.model.local.ShipImpl
import javax.inject.Inject

@FragmentScope
class LaunchLocalRepositoryImpl @Inject constructor(
    private val launchDao: LaunchDao,
    private val missionDao: MissionDao,
    private val rocketLocalRepository: RocketLocalRepositoryAlias,
    private val shipDao: ShipDao,
    private val launchSiteDao: LaunchSiteDao,
    private val launchFailureDetailsDao: LaunchFailureDetailsDao,
    private val linksDao: LinksDao,
    private val launchToMissionDao: LaunchToMissionDao,
    private val launchToShipDao: LaunchToShipDao
) :
    LaunchLocalRepository<LaunchImpl> {

    override fun insertList(launches: List<LaunchImpl>) {
        insertRockets(launches)
        insertLaunchSites(launches)

        launchDao.insertList(launches)

        insertLaunchFailureDetails(launches)
        insertLinks(launches)
        insertMissions(launches)
        insertShips(launches)
    }

    override fun getList(limit: Int) = launchDao.getList(limit)

    private fun insertMissions(launches: List<LaunchImpl>) {
        val missions = ArrayList<MissionImpl>()
        val launchToMissions = ArrayList<LaunchToMissionImpl>()

        launches.forEach { launch ->
            launch.missions?.let { it ->
                missions.addAll(it)

                it.forEach {
                    launchToMissions.add(LaunchToMissionImpl(launch.id, it.id))
                }
            }
        }

        if (missions.isNotEmpty()) {
            missionDao.insertList(missions)
        }

        if (launchToMissions.isNotEmpty()) {
            launchToMissionDao.insertList(launchToMissions)
        }
    }

    private fun insertRockets(launches: List<LaunchImpl>) {
        val rockets = ArrayList<RocketImpl>()

        launches.forEach {
            it.rocket?.let {
                rockets.add(it)
            }
        }

        if (rockets.isNotEmpty()) {
            rocketLocalRepository.insertList(rockets)
        }
    }

    private fun insertShips(launches: List<LaunchImpl>) {
        val ships = ArrayList<ShipImpl>()
        val launchToShips = ArrayList<LaunchToShipImpl>()

        launches.forEach { launch ->
            launch.ships?.let { it ->
                ships.addAll(it)

                it.forEach {
                    launchToShips.add(LaunchToShipImpl(launch.id, it.id))
                }
            }
        }

        if (ships.isNotEmpty()) {
            shipDao.insertList(ships)
        }

        if (launchToShips.isNotEmpty()) {
            launchToShipDao.insertList(launchToShips)
        }
    }

    private fun insertLaunchSites(launches: List<LaunchImpl>) {
        val launchSites = ArrayList<LaunchSiteImpl>()

        launches.forEach {
            it.launch_site?.let {
                launchSites.add(it)
            }
        }

        if (launchSites.isNotEmpty()) {
            launchSiteDao.insertList(launchSites)
        }
    }

    private fun insertLaunchFailureDetails(launches: List<LaunchImpl>) {
        val launchFailureDetails = ArrayList<LaunchFailureDetailsImpl>()

        launches.forEach {
            it.launch_failure_details?.let {
                launchFailureDetails.add(it)
            }
        }

        if (launchFailureDetails.isNotEmpty()) {
            launchFailureDetailsDao.insertList(launchFailureDetails)
        }
    }

    private fun insertLinks(launches: List<LaunchImpl>) {
        val links = ArrayList<LinksImpl>()

        launches.forEach {
            it.links?.let {
                links.add(it)
            }
        }

        if (links.isNotEmpty()) {
            linksDao.insertList(links)
        }
    }
}