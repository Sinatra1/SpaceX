package com.vladislav.shumilov.design_ui.views.carousel.view

import com.vladislav.shumilov.design_ui.databinding.DesignCarouselViewPageBinding
import com.vladislav.shumilov.design_ui.views.carousel.model.CarouselItemModel

internal class CarouselViewHolder(
    private val binding: DesignCarouselViewPageBinding
) : CarouselViewHolderAbstract(binding.root) {

    override fun bind(item: CarouselItemModel) {
        this.item = item

        binding.viewHolder = this
        binding.executePendingBindings()
    }
}