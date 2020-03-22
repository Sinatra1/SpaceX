package com.vladislav.shumilov.auth_ui.di

import com.vladislav.shumilov.auth_ui.ui.AuthFragment
import dagger.Subcomponent

@Subcomponent(modules = [AuthModule::class])
@AuthScope
interface AuthComponent {

    fun inject(fragment: AuthFragment)
}