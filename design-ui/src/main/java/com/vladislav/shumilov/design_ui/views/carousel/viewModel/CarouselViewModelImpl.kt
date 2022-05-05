package com.vladislav.shumilov.design_ui.views.carousel.viewModel

import androidx.databinding.ObservableInt
import com.vladislav.shumilov.core_data.coroutines.CloseableCoroutineScope
import com.vladislav.shumilov.core_data.util.flowInterval
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class CarouselViewModelImpl @Inject constructor() : CarouselViewModel {

    private val currentItemIndex = ObservableInt(START_CAROUSEL_INDEX)
    private var isCarouselStarted = false
    private var delay = 0L
    private var count = 0
    private var scope: CloseableCoroutineScope? = null

    override val onPageSelectedListener = { position: Int ->
        if (currentItemIndex.get() != position) {
            pauseCarousel()
            currentItemIndex.set(position)

            scope?.launch {
                delay(delay)
                resumeCarousel()
            }
        }
    }

    override fun startCarousel(start: Int, count: Int, delay: Long) {
        this.delay = delay
        this.count = count
        currentItemIndex.set(start)
        isCarouselStarted = true

        scope = CloseableCoroutineScope(IO + SupervisorJob())
        scope?.launch {
                flowInterval(delay)
                    .collect {
                        setCurrentItemIndex(start, count)
                    }
            }
    }

    override fun stopCarousel() {
        currentItemIndex.set(START_CAROUSEL_INDEX)
        scope?.close()
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