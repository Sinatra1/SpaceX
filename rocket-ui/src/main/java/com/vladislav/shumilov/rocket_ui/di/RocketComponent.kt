package com.vladislav.shumilov.rocket_ui.di

import com.vladislav.shumilov.core_data.FragmentScope
import dagger.Subcomponent

@Subcomponent(modules = [RocketModule::class])
@FragmentScope
interface RocketComponent