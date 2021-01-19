package com.vladislav.shumilov.design_ui.views.carousel.model

import android.net.Uri

interface CarouselItemModel {
    val imageUri: Uri
    val title: String
    val selected: Boolean
}