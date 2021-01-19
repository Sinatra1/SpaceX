package com.vladislav.shumilov.design_ui.views.carousel.view

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.vladislav.shumilov.design_ui.views.carousel.model.CarouselItemModel

abstract class CarouselAdapterAbstract(context: Context) :
    RecyclerView.Adapter<CarouselViewHolderAbstract>() {
    protected val items: ArrayList<CarouselItemModel> = ArrayList()
    protected val layoutInflater = LayoutInflater.from(context)

    override fun getItemCount() = items.size

    abstract fun setItems(items: List<CarouselItemModel>)
}