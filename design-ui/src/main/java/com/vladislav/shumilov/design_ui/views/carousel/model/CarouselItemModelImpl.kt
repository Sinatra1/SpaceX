package com.vladislav.shumilov.design_ui.views.carousel.model

import android.net.Uri

data class CarouselItemModelImpl(
    override val imageUri: Uri,
    override val title: String,
    override val selected: Boolean
) : CarouselItemModel