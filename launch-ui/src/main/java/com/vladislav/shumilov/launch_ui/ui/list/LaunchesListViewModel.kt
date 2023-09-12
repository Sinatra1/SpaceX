package com.vladislav.shumilov.launch_ui.ui.list

import android.content.res.Resources
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
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@VisibleForTesting
const val FIRST_FLIGHT_NUMBER = 1

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

        viewModelScope.launch(IO) {
            runCatching {
                interactor.getLaunchesForList(state.offset, LAUNCHES_LIMIT)
            }
                .onSuccess(::onLoadedLaunchesSuccess)
                .onFailure { onLoadedLaunchesError() }
        }
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