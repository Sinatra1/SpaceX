package com.vladislav.shumilov.launch_ui.ui.detail

import android.content.res.Resources
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemModel
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_domain.model.local.LaunchForDetail
import com.vladislav.shumilov.launch_ui.common.LaunchInteractorImpl
import com.vladislav.shumilov.launch_ui.util.getRocketDetailCardViewItemsForLaunch
import io.reactivex.disposables.CompositeDisposable

@FragmentScope
class LaunchDetailViewModel(
    private val launchInteractor: LaunchInteractorImpl,
    private val resources: Resources
) : ViewModel() {

    private val launchLiveData = MutableLiveData<LaunchForDetail>()
    val launchForDetail = ObservableField<LaunchForDetail>()
    val rocketDetails = ObservableField<List<CardWithListItemModel>>()

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