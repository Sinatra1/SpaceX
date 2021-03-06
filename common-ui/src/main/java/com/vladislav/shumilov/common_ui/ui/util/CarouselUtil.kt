package com.vladislav.shumilov.common_ui.ui.util

import com.vladislav.shumilov.design_ui.views.carousel.model.CarouselItemModel
import com.vladislav.shumilov.design_ui.views.carousel.model.CarouselItemModelImpl

const val CAROUSEL_DELAY = 5000L

fun prepareCarouselItems(imageUrls: List<String>?): List<CarouselItemModel> =
    mutableListOf<CarouselItemModel>().apply {
        imageUrls?.forEach {
            add(CarouselItemModelImpl(it))
        }
    }