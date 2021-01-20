package com.vladislav.shumilov.design_ui.views.carousel.model

interface CarouselItemModel {
    val imageUrl: String
    val placeholderImageRes: Int?
    val title: String?
    val selected: Boolean
}