package com.vladislav.shumilov.launch_ui.ui.detail

import android.content.res.Resources
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.vladislav.shumilov.common_domain.card_view_with_list.model.CardWithListItemModel
import com.vladislav.shumilov.common_ui.ui.util.CAROUSEL_DELAY
import com.vladislav.shumilov.common_ui.ui.util.prepareCarouselItems
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_data.util.plusAssign
import com.vladislav.shumilov.design_ui.views.carousel.model.CarouselItemModel
import com.vladislav.shumilov.design_ui.views.carousel.viewModel.CarouselViewModel
import com.vladislav.shumilov.design_ui.views.carousel.viewModel.START_CAROUSEL_INDEX
import com.vladislav.shumilov.launch_domain.model.local.LaunchForDetail
import com.vladislav.shumilov.launch_domain.ui.LaunchInteractor
import com.vladislav.shumilov.launch_ui.util.getRocketDetailCardViewItemsForLaunch
import io.reactivex.disposables.CompositeDisposable

@FragmentScope
class LaunchDetailViewModel(
    private val launchInteractor: LaunchInteractor,
    private val resources: Resources,
    val carouselVM: CarouselViewModel
) : ViewModel(), LifecycleObserver {

    private val launchLiveData = MutableLiveData<LaunchForDetail>()
    val launchForDetail = ObservableField<LaunchForDetail>()
    val rocketDetails = ObservableField<List<CardWithListItemModel>>()
    val launchCarouselImages = ObservableField<List<CarouselItemModel>>()
    val isCarouselVisible = ObservableBoolean(false)
    private val title = MutableLiveData<String>()

    private val compositeDisposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        carouselVM.resumeCarousel()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        carouselVM.pauseCarousel()
    }

    fun getLaunchForDetail(launchId: String) {

        compositeDisposable += launchInteractor.getLaunchForDetail(launchId)
            .subscribe({ launch ->
                onLoadedLaunchSuccess(launch)
            }, {
                onLoadedLaunchError()
            })
    }

    fun getTitle(): LiveData<String> = title

    override fun onCleared() {
        carouselVM.stopCarousel()
        compositeDisposable.clear()
    }

    private fun onLoadedLaunchSuccess(launch: LaunchForDetail) {
        launchLiveData.value = launch
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