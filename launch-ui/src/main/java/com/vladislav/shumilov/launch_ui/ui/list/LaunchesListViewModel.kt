package com.vladislav.shumilov.launch_ui.ui.list

import android.content.res.Resources
import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_ui.delegates.DefaultStateDelegate
import com.vladislav.shumilov.core_ui.models.StateDelegate
import com.vladislav.shumilov.launch_data.api.LAUNCHES_LIMIT
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList
import com.vladislav.shumilov.launch_domain.ui.LaunchInteractor
import com.vladislav.shumilov.launch_ui.R
import com.vladislav.shumilov.launch_ui.models.LaunchesListState
import com.vladislav.shumilov.launch_ui.models.START_OFFSET
import com.vladislav.shumilov.launch_ui.ui.detail.LaunchDetailFragment
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@VisibleForTesting
const val FIRST_FLIGHT_NUMBER = 1
private const val TAG = "parallel_launches"

@FragmentScope
internal class LaunchesListViewModel @Inject constructor(
    private val interactor: LaunchInteractor,
    resources: Resources,
) : ViewModel(), StateDelegate<LaunchesListState> by DefaultStateDelegate(LaunchesListState()) {

    val isShownCenterProgress = ObservableBoolean(true)
    val isShownBottomProgress = ObservableBoolean(false)

    private lateinit var navController: NavController
    private val missionIconTransitionName: String =
        resources.getString(R.string.launches_mission_icon_transition_name)

    fun getLaunchesForList() {
        if (state.isInProgress || state.isLastPage) {
            return
        }

        updateState {
            copy(
                isInProgress = true,
            )
        }

        setIsShownProgress(true)

        viewModelScope.launch(IO + launchesExceptionViewHolder) {
            executeAllRequestsInParallel(state.offset)
            executeAllRequestsInParallel(state.offset + 2 * LAUNCHES_LIMIT)
        }
    }

    private suspend fun CoroutineScope.executeAllRequestsInParallel(offset: Int) {
        Log.d(TAG, "parallel request started ${Thread.currentThread().name}")
        val deferred1 = async {
            runCatching {
                Log.d(TAG, "request1 start ${Thread.currentThread().name}")
                val request1 = getLaunchesForListRequest1(offset)
                Log.d(TAG, "request1 end")
                request1
            }.onFailure {
                Log.d(TAG, "request1 error ${it.localizedMessage}")
            }.getOrNull().orEmpty()
        }
        val deferred2 = async {
            runCatching {
                Log.d(TAG, "request2 start ${Thread.currentThread().name}")
                val request2 = getLaunchesForListRequest2(offset + LAUNCHES_LIMIT)
                Log.d(TAG, "request2 end")
                request2
            }.onFailure {
                Log.d(TAG, "request2 error ${it.localizedMessage}")
            }.getOrNull().orEmpty()
        }

        val launches = mutableListOf<LaunchForList>()

        awaitAll(deferred1, deferred2).forEach {
            launches.addAll(it)
        }

        onLoadedLaunchesSuccess(launches)

        Log.d(TAG, "parallel request ended ${Thread.currentThread().name}")
    }

    private suspend fun getLaunchesForListRequest1(offset: Int) =
        interactor.getLaunchesForList(offset, LAUNCHES_LIMIT)

    private suspend fun getLaunchesForListRequest2(offset: Int) =
        interactor.getLaunchesForList(offset, LAUNCHES_LIMIT)

    private val launchesExceptionViewHolder = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "parallel request error ${throwable.localizedMessage} ${Thread.currentThread().name}")
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun showLaunchDetailFragment(launchId: String) {
        navController.navigate(
            R.id.common_action_launchesListWithDetailFragment_to_launchDetailFragment,
            LaunchDetailFragment.getBundle(launchId),
            null,
        )
    }

    private fun onLoadedLaunchesSuccess(newLaunches: List<LaunchForList>) {
        setIsShownProgress(false)

        if (newLaunches.isNotEmpty()) {
            updateState {
                copy(
                    isInProgress = false,
                    isShownRefreshingIcon = false,
                    offset = offset + LAUNCHES_LIMIT,
                    isLastPage = isFirstFlight(newLaunches.last()),
                    launches = mutableListOf<LaunchForList>().apply {
                        addAll(launches)
                        addAll(newLaunches)
                    },
                )
            }
        } else {
            onFinishLoading()
        }
    }

    private fun onLoadedLaunchesError() {
        setIsShownProgress(false)
        onFinishLoading()
    }

    private fun onFinishLoading() {
        setIsShownProgress(false)

        updateState {
            copy(
                isInProgress = false,
                isShownRefreshingIcon = false,
            )
        }
    }

    private fun setIsShownProgress(showProgress: Boolean) {
        isShownCenterProgress.set(showProgress && state.offset == START_OFFSET)
        isShownBottomProgress.set(showProgress && state.offset > START_OFFSET)
    }

    private fun isFirstFlight(launchForList: LaunchForList) =
        launchForList.launch.flightNumber == FIRST_FLIGHT_NUMBER
}