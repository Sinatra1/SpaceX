package com.vladislav.shumilov.launch_ui.ui.list

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_ui.utils.navigate
import com.vladislav.shumilov.launch_data.api.LAUNCHES_LIMIT
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList
import com.vladislav.shumilov.launch_ui.common.LaunchInteractorImpl
import com.vladislav.shumilov.launch_ui.ui.detail.LaunchDetailFragment
import io.reactivex.disposables.CompositeDisposable

@FragmentScope
class LaunchesListViewModel(private val launchInteractor: LaunchInteractorImpl) : ViewModel() {

    private val launchesLiveData = MutableLiveData<List<LaunchForList>>().apply {
        value = mutableListOf()
    }

    val isShownProgress = ObservableField<Boolean>().apply { set(false) }
    private var offset = 0
    private val inProcessLiveData = MutableLiveData<Boolean>().apply { value = false }
    private val isLastPageLiveData = MutableLiveData<Boolean>().apply { value = false }
    private val isShownRefreshingIcon = MutableLiveData<Boolean>()
    private val compositeDisposable = CompositeDisposable()
    private lateinit var navController: NavController


    fun getLaunchesForList() {
        if (inProcessLiveData.value == true) {
            return
        }

        inProcessLiveData.postValue(true)
        isShownProgress.set(inProcessLiveData.value)

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

    fun getInProcess(): LiveData<Boolean> = inProcessLiveData

    fun getIsLastPage(): LiveData<Boolean> = isLastPageLiveData
    
    fun getLaunches(): LiveData<List<LaunchForList>> = launchesLiveData

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun showLaunchDetail(launchId: String) {
        navController.navigate(LaunchDetailFragment::class.java, LaunchDetailFragment.getBundle(launchId))
    }

    private fun onLoadedLaunchesSuccess(launches: List<LaunchForList>) {
        inProcessLiveData.postValue(false)
        isShownProgress.set(inProcessLiveData.value)
        isShownRefreshingIcon.postValue(inProcessLiveData.value)
        offset += LAUNCHES_LIMIT

        if (launches.isNotEmpty()) {
            val allLaunches = launchesLiveData.value as ArrayList<LaunchForList>
            allLaunches.addAll(launches)

            launchesLiveData.postValue(allLaunches)

            if (isLastPageLiveData.value != true) {
                isLastPageLiveData.postValue(launches.last().launch.flightNumber == 0)
            }
        }
    }

    private fun onLoadedLaunchesError() {
        inProcessLiveData.postValue(false)
        isShownProgress.set(inProcessLiveData.value)
        isShownRefreshingIcon.postValue(inProcessLiveData.value)
    }
}