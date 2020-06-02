package com.vladislav.shumilov.launch_ui.ui

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_data.api.LAUNCHES_LIMIT
import com.vladislav.shumilov.launch_data.model.local.LaunchWithMissionsImpl
import com.vladislav.shumilov.launch_ui.common.LaunchInteractorImpl

@FragmentScope
class LaunchesListViewModel(private val launchInteractor: LaunchInteractorImpl) : ViewModel() {

    private val launchesWithMissions = MutableLiveData<List<LaunchWithMissionsImpl>>().apply {
        value = ArrayList()
    }

    val isShownProgress = ObservableField<Boolean>().apply { false }
    private var offset = 0
    private val inProcess = MutableLiveData<Boolean>().apply { value = false }
    private val isLastPage = MutableLiveData<Boolean>().apply { value = false }
    private val isShownRefreshingIcon = MutableLiveData<Boolean>()


    fun getListWithMissions() {
        if (inProcess.value == true) {
            return
        }

        inProcess.postValue(true)
        isShownProgress.set(inProcess.value)

        launchInteractor.getListWithMissions(offset, LAUNCHES_LIMIT)
            .subscribe({ launches ->
                onLoadedLaunchesSuccess(launches)
            }, { error ->
                onLoadedLaunchesError(error)
            })
    }

    val launchesWithMissionsLiveData: LiveData<List<LaunchWithMissionsImpl>> = launchesWithMissions

    val inProcessLiveData: LiveData<Boolean> = inProcess

    val isLastPageLiveData: LiveData<Boolean> = isLastPage

    private fun onLoadedLaunchesSuccess(launches: List<LaunchWithMissionsImpl>) {
        inProcess.postValue(false)
        isShownProgress.set(inProcess.value)
        isShownRefreshingIcon.postValue(inProcess.value)
        offset += LAUNCHES_LIMIT

        if (launches.isNotEmpty()) {
            val allLaunches = launchesWithMissions.value as ArrayList<LaunchWithMissionsImpl>
            allLaunches.addAll(launches)

            launchesWithMissions.postValue(allLaunches)

            if (isLastPage.value != true) {
                isLastPage.postValue(launches.last().launch.flight_number == 0)
            }
        }
    }

    private fun onLoadedLaunchesError(error: Throwable) {
        inProcess.postValue(false)
        isShownProgress.set(inProcess.value)
        isShownRefreshingIcon.postValue(inProcess.value)
    }
}