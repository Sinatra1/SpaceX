package com.vladislav.shumilov.launch_ui.ui.detail

import android.content.res.Resources
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemModel
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_data.util.plusAssign
import com.vladislav.shumilov.launch_domain.model.local.LaunchForDetail
import com.vladislav.shumilov.launch_domain.ui.LaunchInteractor
import com.vladislav.shumilov.launch_ui.util.getRocketDetailCardViewItemsForLaunch
import io.reactivex.disposables.CompositeDisposable

internal class LaunchDetailViewModel(
    private val launchInteractor: LaunchInteractor,
    private val resources: Resources,
    private val launchId: String
) : ViewModel(), LifecycleObserver {

    private val launchLiveData = MutableLiveData<LaunchForDetail>()
    val launchForDetail = ObservableField<LaunchForDetail>()
    val rocketDetails = ObservableField<List<CardWithListItemModel>>()

    private val compositeDisposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        getLaunchForDetail()
    }

    private fun getLaunchForDetail() {
        compositeDisposable += launchInteractor.getLaunchForDetail(launchId)
            .subscribe({ launch ->
                onLoadedLaunchSuccess(launch)
            }, {
                onLoadedLaunchError()
            })
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }

    private fun onLoadedLaunchSuccess(launch: LaunchForDetail) {
        launchLiveData.value = launch
        launchForDetail.set(launch)

        val details = getRocketDetailCardViewItemsForLaunch(launch, resources)

        rocketDetails.set(details)
    }

    private fun onLoadedLaunchError() {

    }
}