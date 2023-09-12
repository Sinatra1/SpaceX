package com.vladislav.shumilov.launch_ui.ui.detail

import android.content.res.Resources
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemModel
import com.vladislav.shumilov.common_ui.ui.util.CAROUSEL_DELAY
import com.vladislav.shumilov.common_ui.ui.util.prepareCarouselItems
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_ui.delegates.DefaultStateDelegate
import com.vladislav.shumilov.core_ui.models.StateDelegate
import com.vladislav.shumilov.design_ui.views.carousel.model.CarouselItemModel
import com.vladislav.shumilov.design_ui.views.carousel.viewModel.CarouselViewModel
import com.vladislav.shumilov.design_ui.views.carousel.viewModel.START_CAROUSEL_INDEX
import com.vladislav.shumilov.launch_domain.model.local.LaunchForDetail
import com.vladislav.shumilov.launch_domain.ui.LaunchInteractor
import com.vladislav.shumilov.launch_ui.models.LaunchDetailState
import com.vladislav.shumilov.launch_ui.models.LaunchesListState
import com.vladislav.shumilov.launch_ui.util.getRocketDetailCardViewItemsForLaunch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@FragmentScope
internal class LaunchDetailViewModel @Inject constructor(
    private val interactor: LaunchInteractor,
    private val resources: Resources,
    val carouselVM: CarouselViewModel
) : ViewModel(), LifecycleObserver, StateDelegate<LaunchDetailState> by DefaultStateDelegate(LaunchDetailState()) {

    val launchForDetail = ObservableField<LaunchForDetail>()
    val rocketDetails = ObservableField<List<CardWithListItemModel>>()
    val launchCarouselImages = ObservableField<List<CarouselItemModel>>()
    val isCarouselVisible = ObservableBoolean(false)

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        carouselVM.resumeCarousel()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        carouselVM.pauseCarousel()
    }

    fun getLaunchForDetail(launchId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                interactor.getLaunchForDetail(launchId)
            }
                .onSuccess(::onLoadedLaunchSuccess)
                .onFailure { onLoadedLaunchError() }
        }
    }

    override fun onCleared() {
        carouselVM.stopCarousel()
    }

    private fun onLoadedLaunchSuccess(launch: LaunchForDetail) {
        updateState {
            copy(
                launch = launch,
            )
        }

        launchForDetail.set(launch)

        val details = getRocketDetailCardViewItemsForLaunch(launch, resources)

        rocketDetails.set(details)

        launchCarouselImages.set(prepareCarouselItems(launch.links?.flickrImages))

        launchCarouselImages.get()?.size?.let {
            if (it > 0) {
                carouselVM.startCarousel(START_CAROUSEL_INDEX, it, CAROUSEL_DELAY)
                isCarouselVisible.set(true)
            }
        }
    }

    private fun onLoadedLaunchError() {

    }
}