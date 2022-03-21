package com.vladislav.shumilov.launch_ui.ui.list

import android.content.res.Resources
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_data.util.plusAssign
import com.vladislav.shumilov.launch_data.api.LAUNCHES_LIMIT
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList
import com.vladislav.shumilov.launch_domain.ui.LaunchInteractor
import com.vladislav.shumilov.launch_ui.ui.detail.LaunchDetailFragment
import io.reactivex.disposables.CompositeDisposable
import com.vladislav.shumilov.launch_ui.R

@VisibleForTesting
const val FIRST_FLIGHT_NUMBER = 1

@VisibleForTesting
const val START_OFFSET = 0

@FragmentScope
internal class LaunchesListViewModel(
    private val interactor: LaunchInteractor,
    resources: Resources
) : ViewModel() {

    private val launchesLiveData = MutableLiveData<List<LaunchForList>>().apply {
        value = mutableListOf()
    }

    val isShownCenterProgress = ObservableBoolean(true)
    val isShownBottomProgress = ObservableBoolean(false)

    private val inProcessLiveData = MutableLiveData<Boolean>(false)
    private val isLastPageLiveData = MutableLiveData<Boolean>(false)
    private val isShownRefreshingIcon = MutableLiveData<Boolean>()
    private var offset = START_OFFSET
    private val compositeDisposable = CompositeDisposable()
    private lateinit var navController: NavController
    private val missionIconTransitionName: String =
        resources.getString(R.string.launches_mission_icon_transition_name)

    fun getLaunchesForList() {
        if (inProcessLiveData.value == true || isLastPageLiveData.value == true) {
            return
        }

        inProcessLiveData.postValue(true)
        setIsShownProgress(true)

        compositeDisposable.clear()

        compositeDisposable += interactor.getLaunchesForList(offset, LAUNCHES_LIMIT)
            .subscribe({ launches ->
                onLoadedLaunchesSuccess(launches)
            }, {
                onLoadedLaunchesError()
            })
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }

    fun getInProcess(): LiveData<Boolean> = inProcessLiveData

    fun getIsLastPage(): LiveData<Boolean> = isLastPageLiveData

    fun getLaunches(): LiveData<List<LaunchForList>> = launchesLiveData

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun showLaunchDetailFragment(launchId: String, transitionView: View?) {
        val extras =
            if (transitionView != null)
                FragmentNavigatorExtras(transitionView to missionIconTransitionName)
            else null

        navController.navigate(
            R.id.common_action_launchesListWithDetailFragment_to_launchDetailFragment,
            LaunchDetailFragment.getBundle(launchId),
            null,
            extras
        )
    }

    private fun onLoadedLaunchesSuccess(launches: List<LaunchForList>) {
        setIsShownProgress(false)
        inProcessLiveData.postValue(false)
        isShownRefreshingIcon.postValue(inProcessLiveData.value)
        offset += LAUNCHES_LIMIT

        if (launches.isNotEmpty()) {
            val allLaunches = launchesLiveData.value as ArrayList<LaunchForList>

            allLaunches.addAll(launches)

            launchesLiveData.postValue(allLaunches)

            if (isLastPageLiveData.value != true) {
                isLastPageLiveData.postValue(isFirstFlight(launches.last()))
            }
        }
    }

    private fun onLoadedLaunchesError() {
        setIsShownProgress(false)
        inProcessLiveData.postValue(false)
        isShownRefreshingIcon.postValue(inProcessLiveData.value)
    }

    private fun setIsShownProgress(showProgress: Boolean) {
        isShownCenterProgress.set(showProgress && offset == START_OFFSET)
        isShownBottomProgress.set(showProgress && offset > START_OFFSET)
    }

    private fun isFirstFlight(launchForList: LaunchForList) =
        launchForList.launch.flightNumber == FIRST_FLIGHT_NUMBER
}