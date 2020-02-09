package com.vladislav.shumilov.mytwitter.components

import com.vladislav.shumilov.auth_ui.AuthFragment
import dagger.Subcomponent
import javax.inject.Scope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class AuthScope

@Subcomponent
@AuthScope
interface AuthComponent {

    fun inject(fragment: AuthFragment)
}