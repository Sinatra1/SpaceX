package com.vladislav.shumilov.design_ui.views.carousel.view

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.vladislav.shumilov.design_ui.R
import com.vladislav.shumilov.design_ui.databinding.DesignCarouselViewPageBinding
import com.vladislav.shumilov.design_ui.views.carousel.model.CarouselItemModel

internal class CarouselAdapter(context: Context) : CarouselAdapterAbstract(context) {

    private lateinit var binding: DesignCarouselViewPageBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolderAbstract {
        binding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.design_carousel_view_page,
                parent,
                false
            )

        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolderAbstract, position: Int) {
        holder.bind(items[position])
    }

    override fun setItems(items: List<CarouselItemModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}