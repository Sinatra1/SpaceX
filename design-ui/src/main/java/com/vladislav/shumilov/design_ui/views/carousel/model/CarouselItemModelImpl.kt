package com.vladislav.shumilov.design_ui.views.carousel.model

import androidx.annotation.DrawableRes

data class CarouselItemModelImpl(
    override val imageUrl: String,
    @DrawableRes
    override val placeholderImageRes: Int? = null,
    override val title: String? = null,
    override val selected: Boolean = false
) : CarouselItemModel