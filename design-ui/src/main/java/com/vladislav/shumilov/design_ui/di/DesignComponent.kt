package com.vladislav.shumilov.design_ui.di

import com.vladislav.shumilov.design_ui.views.carousel.viewModel.CarouselViewModel
import dagger.Component

@Component(modules = [DesignModule::class])
interface DesignComponent {

    fun getCarouselViewModel(): CarouselViewModel
}