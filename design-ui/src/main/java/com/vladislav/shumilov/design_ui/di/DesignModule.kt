package com.vladislav.shumilov.design_ui.di

import com.vladislav.shumilov.design_ui.views.carousel.viewModel.CarouselViewModel
import com.vladislav.shumilov.design_ui.views.carousel.viewModel.CarouselViewModelImpl
import dagger.Binds
import dagger.Module

@Module
internal abstract class DesignModule {

    @Binds
    abstract fun bindCarouselViewModel(impl: CarouselViewModelImpl): CarouselViewModel
}