package com.vladislav.shumilov.launch_data.repository

import com.example.rocket_domain.model.local.Rocket
import com.example.rocket_domain.repository.RocketLocalRepository
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_data.util.UNCHECKED_CAST
import com.vladislav.shumilov.launch_data.database.*
import com.vladislav.shumilov.launch_data.model.local.*
import com.vladislav.shumilov.launch_domain.model.local.*
import com.vladislav.shumilov.launch_domain.repository.LaunchLocalRepository
import com.vladislav.shumilov.mission_data.database.MissionDao
import com.vladislav.shumilov.mission_data.model.local.MissionImpl
import com.vladislav.shumilov.mission_domain.model.local.Mission
import com.vladislav.shumilov.ship_data.database.ShipDao
import com.vladislav.shumilov.ship_data.model.local.ShipImpl
import com.vladislav.shumilov.ship_domain.model.local.Ship
import io.reactivex.Single
import javax.inject.Inject

@FragmentScope
class LaunchLocalRepositoryImpl @Inject constructor(
    private val launchDao: LaunchDao,
    private val missionDao: MissionDao,
    private val rocketLocalRepository: RocketLocalRepository,
    private val shipDao: ShipDao,
    private val launchSiteDao: LaunchSiteDao,
    private val launchFailureDetailsDao: LaunchFailureDetailsDao,
    private val linksDao: LinksDao,
    private val launchToMissionDao: LaunchToMissionDao,
    private val launchToShipDao: LaunchToShipDao
) :
    LaunchLocalRepository {


    override fun insertList(launches: List<Launch>) {
        if (launches.isEmpty()) return

        insertRockets(launches)
        insertLaunchSites(launches)

        @Suppress(UNCHECKED_CAST)
        launchDao.insertList(launches as List<LaunchImpl>)

        insertLaunchFailureDetails(launches)
        insertLinks(launches)
        insertMissions(launches)
        insertShips(launches)
    }

    @Suppress(UNCHECKED_CAST)
    override fun getList(limit: Int) = launchDao.getList(limit) as Single<List<Launch>>

    @Suppress(UNCHECKED_CAST)
    override fun getListWithMissions(offset: Int, limit: Int) =
        launchDao.getListWithMissions(offset, limit) as Single<List<LaunchWithMissions>>

    override fun getListWithMissionsByList(launches: List<Launch>): List<LaunchWithMissions> {
        val launchesWithMissions = mutableListOf<LaunchWithMissionsImpl>()

        launches.forEach {
            launchesWithMissions.add(
                @Suppress(UNCHECKED_CAST)
                LaunchWithMissionsImpl(
                    it as LaunchImpl,
                    (it.missions ?: emptyList()) as List<MissionImpl>
                )
            )
        }

        return launchesWithMissions
    }

    private fun insertMissions(launches: List<Launch>) {
        val missionsList = mutableListOf<Mission>()
        val launchToMissions = mutableListOf<LaunchToMissionImpl>()

        launches.forEach { launch ->
            launch.missions?.let { missions ->
                missionsList.addAll(missions)

                missions.forEach {
                    launchToMissions.add(LaunchToMissionImpl(launch.id, it.id))
                }
            }
        }

        if (missionsList.isNotEmpty()) {
            @Suppress(UNCHECKED_CAST)
            missionDao.insertList(missionsList as List<MissionImpl>)
        }

        if (launchToMissions.isNotEmpty()) {
            launchToMissionDao.insertList(launchToMissions)
        }
    }

    private fun insertRockets(launches: List<Launch>) {
        val rockets = mutableListOf<Rocket>()

        launches.forEach {
            it.rocket?.let(rockets::add)
        }

        if (rockets.isNotEmpty()) {
            rocketLocalRepository.insertList(rockets)
        }
    }

    private fun insertShips(launches: List<Launch>) {
        val shipsList = mutableListOf<Ship>()
        val launchToShips = mutableListOf<LaunchToShipImpl>()

        launches.forEach { launch ->
            launch.ships?.let { ships ->
                shipsList.addAll(ships)

                ships.forEach {
                    launchToShips.add(LaunchToShipImpl(launch.id, it.id))
                }
            }
        }

        if (shipsList.isNotEmpty()) {
            @Suppress(UNCHECKED_CAST)
            shipDao.insertList(shipsList as List<ShipImpl>)
        }

        if (launchToShips.isNotEmpty()) {
            launchToShipDao.insertList(launchToShips)
        }
    }

    private fun insertLaunchSites(launches: List<Launch>) {
        val launchSites = mutableListOf<LaunchSite>()

        launches.forEach {
            it.launchSite?.let(launchSites::add)
        }

        if (launchSites.isNotEmpty()) {
            @Suppress(UNCHECKED_CAST)
            launchSiteDao.insertList(launchSites as List<LaunchSiteImpl>)
        }
    }

    private fun insertLaunchFailureDetails(launches: List<Launch>) {
        val launchFailureDetailsList = mutableListOf<LaunchFailureDetails>()

        launches.forEach {
            it.launchFailureDetails?.let(launchFailureDetailsList::add)
        }

        if (launchFailureDetailsList.isNotEmpty()) {
            @Suppress(UNCHECKED_CAST)
            launchFailureDetailsDao.insertList(launchFailureDetailsList as List<LaunchFailureDetailsImpl>)
        }
    }

    private fun insertLinks(launches: List<Launch>) {
        val linksList = mutableListOf<Links>()

        launches.forEach {
            it.links?.let(linksList::add)
        }

        if (linksList.isNotEmpty()) {
            @Suppress(UNCHECKED_CAST)
            linksDao.insertList(linksList as List<LinksImpl>)
        }
    }
}