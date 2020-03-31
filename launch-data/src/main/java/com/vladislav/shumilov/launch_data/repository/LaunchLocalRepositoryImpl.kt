package com.vladislav.shumilov.launch_data.repository

import com.example.rocket_data.database.RocketDao
import com.example.rocket_data.model.local.RocketImpl
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_data.database.LaunchDao
import com.vladislav.shumilov.launch_data.database.LaunchFailureDetailsDao
import com.vladislav.shumilov.launch_data.database.LaunchSiteDao
import com.vladislav.shumilov.launch_data.database.LinksDao
import com.vladislav.shumilov.launch_data.model.local.LaunchFailureDetailsImpl
import com.vladislav.shumilov.launch_data.model.local.LaunchImpl
import com.vladislav.shumilov.launch_data.model.local.LaunchSiteImpl
import com.vladislav.shumilov.launch_data.model.local.LinksImpl
import com.vladislav.shumilov.launch_domain.repository.LaunchLocalRepository
import javax.inject.Inject

@FragmentScope
class LaunchLocalRepositoryImpl @Inject constructor(
    private val launchDao: LaunchDao,
    private val rocketDao: RocketDao,
    private val launchSiteDao: LaunchSiteDao,
    private val launchFailureDetailsDao: LaunchFailureDetailsDao,
    private val linksDao: LinksDao
) :
    LaunchLocalRepository<LaunchImpl> {

    override fun insertList(launches: List<LaunchImpl>) {
        insertRockets(launches)
        insertLaunchSites(launches)

        launchDao.insertList(launches)

        insertLaunchFailureDetails(launches)
        insertLinks(launches)
    }

    override fun getList() = launchDao.getList()

    private fun insertRockets(launches: List<LaunchImpl>) {
        val rockets = ArrayList<RocketImpl>()

        launches.forEach {
            it.rocket?.let {
                rockets.add(it)
            }
        }

        if (rockets.isNotEmpty()) {
            rocketDao.insertList(rockets)
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