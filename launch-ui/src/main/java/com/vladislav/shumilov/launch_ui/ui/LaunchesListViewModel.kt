package com.vladislav.shumilov.launch_ui.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_data.model.local.LaunchWithMissionsImpl
import com.vladislav.shumilov.launch_ui.common.LaunchInteractor

@FragmentScope
class LaunchesListViewModel(private val launchInteractor: LaunchInteractor) : ViewModel() {

    private val launchesWithMissions = MutableLiveData<List<LaunchWithMissionsImpl>>()

    fun getListWithMissions() {
        launchInteractor.getListWithMissions().subscribe { launches ->
            this.launchesWithMissions.postValue(launches)
        }
    }

    fun getLaunchesWithMissions(): LiveData<List<LaunchWithMissionsImpl>> = launchesWithMissions
}