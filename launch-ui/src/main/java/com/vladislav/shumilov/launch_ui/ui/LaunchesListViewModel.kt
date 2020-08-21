package com.vladislav.shumilov.launch_ui.ui

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_data.api.LAUNCHES_LIMIT
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList
import com.vladislav.shumilov.launch_ui.common.LaunchInteractorImpl
import io.reactivex.disposables.CompositeDisposable

@FragmentScope
class LaunchesListViewModel(private val launchInteractor: LaunchInteractorImpl) : ViewModel() {

    private val launchesWithMissions = MutableLiveData<List<LaunchForList>>().apply {
        value = ArrayList()
    }

    val isShownProgress = ObservableField<Boolean>().apply { set(false) }
    private var offset = 0
    private val inProcess = MutableLiveData<Boolean>().apply { value = false }
    private val isLastPage = MutableLiveData<Boolean>().apply { value = false }
    private val isShownRefreshingIcon = MutableLiveData<Boolean>()
    private val compositeDisposable = CompositeDisposable()


    fun getLaunchesForList() {
        if (inProcess.value == true) {
            return
        }

        inProcess.postValue(true)
        isShownProgress.set(inProcess.value)

        compositeDisposable.add(
            launchInteractor.getLaunchesForList(offset, LAUNCHES_LIMIT)
                .subscribe({ launches ->
                    onLoadedLaunchesSuccess(launches)
                }, {
                    onLoadedLaunchesError()
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }

    val launchesForListLiveData: LiveData<List<LaunchForList>> = launchesWithMissions

    val inProcessLiveData: LiveData<Boolean> = inProcess

    val isLastPageLiveData: LiveData<Boolean> = isLastPage

    private fun onLoadedLaunchesSuccess(launches: List<LaunchForList>) {
        inProcess.postValue(false)
        isShownProgress.set(inProcess.value)
        isShownRefreshingIcon.postValue(inProcess.value)
        offset += LAUNCHES_LIMIT

        if (launches.isNotEmpty()) {
            val allLaunches = launchesWithMissions.value as ArrayList<LaunchForList>
            allLaunches.addAll(launches)

            launchesWithMissions.postValue(allLaunches)

            if (isLastPage.value != true) {
                isLastPage.postValue(launches.last().launch.flightNumber == 0)
            }
        }
    }

    private fun onLoadedLaunchesError() {
        inProcess.postValue(false)
        isShownProgress.set(inProcess.value)
        isShownRefreshingIcon.postValue(inProcess.value)
    }
}