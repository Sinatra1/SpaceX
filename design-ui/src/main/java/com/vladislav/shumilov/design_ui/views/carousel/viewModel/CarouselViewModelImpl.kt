package com.vladislav.shumilov.design_ui.views.carousel.viewModel

import androidx.databinding.ObservableInt
import com.vladislav.shumilov.core_data.util.plusAssign
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal class CarouselViewModelImpl @Inject constructor() : CarouselViewModel {

    private val currentItemIndex = ObservableInt(START_CAROUSEL_INDEX)
    private var isCarouselStarted = false
    private val compositeDisposable = CompositeDisposable()

    override val onPageSelectedListener = { position: Int ->
        if (currentItemIndex.get() != position) {
            currentItemIndex.set(position)
        }
    }

    override fun startCarousel(start: Int, count: Int, delay: Long) {
        currentItemIndex.set(start)
        isCarouselStarted = true

        compositeDisposable += Observable.interval(delay, TimeUnit.MILLISECONDS)
            .subscribe(
                {
                    setCurrentItemIndex(start, count)
                }, Timber::e
            )
    }

    override fun stopCarousel() {
        currentItemIndex.set(START_CAROUSEL_INDEX)
        compositeDisposable.clear()
    }

    override fun resumeCarousel() {
        isCarouselStarted = true
    }

    override fun pauseCarousel() {
        isCarouselStarted = false
    }

    override fun getCurrentItemIndex(): ObservableInt = currentItemIndex

    private fun setCurrentItemIndex(start: Int, count: Int) {
        if (!isCarouselStarted) return

        with(currentItemIndex.get()) {
            val nextIndex = this + 1

            if (nextIndex == count) {
                currentItemIndex.set(start)
            } else {
                currentItemIndex.set(nextIndex)
            }
        }
    }
}