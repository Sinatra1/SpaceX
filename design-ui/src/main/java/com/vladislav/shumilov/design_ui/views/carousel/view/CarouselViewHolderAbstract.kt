package com.vladislav.shumilov.design_ui.views.carousel.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vladislav.shumilov.design_ui.views.carousel.model.CarouselItemModel

abstract class CarouselViewHolderAbstract(view: View) :
    RecyclerView.ViewHolder(view) {

    abstract fun bind(item: CarouselItemModel)
}