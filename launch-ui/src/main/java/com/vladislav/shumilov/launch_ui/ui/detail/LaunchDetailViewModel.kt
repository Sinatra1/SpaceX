package com.vladislav.shumilov.launch_ui.ui.detail

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_domain.model.local.LaunchForDetail
import com.vladislav.shumilov.launch_ui.common.LaunchInteractorImpl
import io.reactivex.disposables.CompositeDisposable

@FragmentScope
class LaunchDetailViewModel(private val launchInteractor: LaunchInteractorImpl) : ViewModel() {

    private val launchLiveData = MutableLiveData<LaunchForDetail>()
    val launchForDetail = ObservableField<LaunchForDetail>()

    private val compositeDisposable = CompositeDisposable()

    fun getLaunchForDetail(launchId: String) {
        compositeDisposable.add(
            launchInteractor.getLaunchForDetail(launchId)
                .subscribe({ launch ->
                    onLoadedLaunchSuccess(launch)
                }, {
                    onLoadedLaunchError()
                })
        )
    }

    private fun onLoadedLaunchSuccess(launch: LaunchForDetail) {
        launchLiveData.value = launch
        launchForDetail.set(launch)
    }

    private fun onLoadedLaunchError() {

    }
}