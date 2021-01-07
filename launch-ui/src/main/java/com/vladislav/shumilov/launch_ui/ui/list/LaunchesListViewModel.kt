package com.vladislav.shumilov.launch_ui.ui.list

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_data.util.plusAssign
import com.vladislav.shumilov.core_ui.utils.navigate
import com.vladislav.shumilov.launch_data.api.LAUNCHES_LIMIT
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList
import com.vladislav.shumilov.launch_domain.ui.LaunchInteractor
import com.vladislav.shumilov.launch_ui.ui.detail.LaunchDetailFragment
import io.reactivex.disposables.CompositeDisposable

@FragmentScope
internal class LaunchesListViewModel(private val launchInteractor: LaunchInteractor) : ViewModel() {

    private val launchesLiveData = MutableLiveData<List<LaunchForList>>().apply {
        value = mutableListOf()
    }

    val isShownCenterProgress = ObservableBoolean(true)
    val isShownBottomProgress = ObservableBoolean(false)

    private var offset = START_OFFSET
    private val inProcessLiveData = MutableLiveData<Boolean>().apply { value = false }
    private val isLastPageLiveData = MutableLiveData<Boolean>().apply { value = false }
    private val isShownRefreshingIcon = MutableLiveData<Boolean>()
    private val compositeDisposable = CompositeDisposable()
    private lateinit var navController: NavController

    fun getLaunchesForList() {
        if (inProcessLiveData.value == true || isLastPageLiveData.value == true) {
            return
        }

        inProcessLiveData.postValue(true)
        setIsShownProgress(true)

        compositeDisposable.clear()

        compositeDisposable += launchInteractor.getLaunchesForList(offset, LAUNCHES_LIMIT)
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

    fun showLaunchDetail(launchId: String) {
        navController.navigate(
            LaunchDetailFragment::class.java,
            LaunchDetailFragment.getBundle(launchId)
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

private const val FIRST_FLIGHT_NUMBER = 1
private const val START_OFFSET = 0