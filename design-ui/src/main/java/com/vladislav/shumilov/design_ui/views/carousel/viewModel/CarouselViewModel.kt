package com.vladislav.shumilov.design_ui.views.carousel.viewModel

import androidx.databinding.ObservableInt

const val START_CAROUSEL_INDEX = 0

interface CarouselViewModel {

    val onPageSelectedListener: (position: Int) -> Unit

    fun startCarousel(start: Int, count: Int, delay: Long)

    fun stopCarousel()

    fun resumeCarousel()

    fun pauseCarousel()

    fun getCurrentItemIndex(): ObservableInt
}